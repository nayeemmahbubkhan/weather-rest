package de.creatision.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.creatision.weather.domainobject.User;
import de.creatision.weather.exception.ConstraintsViolationException;
import de.creatision.weather.exception.EntityNotFoundException;
import de.creatision.weather.exception.InvalidCredentialException;
import de.creatision.weather.exception.UserAlreadyExistException;
import de.creatision.weather.repository.UserRepository;
import de.creatision.weather.service.security.BCryptService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserRepository userRepository;
	private BCryptService bCryptService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptService bCryptService) {
		this.userRepository = userRepository;
		this.bCryptService = bCryptService;
	}

	@Override
	@Transactional
	public User signUp(User user) throws ConstraintsViolationException, UserAlreadyExistException {
		
		User existedUser = userRepository.findUserByUsername(user.getUsername());
		
		if(existedUser != null) throw new UserAlreadyExistException("user already exist with the username: " + user.getUsername());
		
		user.setPassword(bCryptService.encode(user.getPassword()));
		
		User userSaved = null;
		try {
			//TODO: validate city name and county
			userSaved = userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("ConstraintsViolationException while creating a user: {}", userSaved, e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		
		return userSaved;
	}

	@Override
	@Transactional(readOnly = true)
	public User login(String username, String password) throws InvalidCredentialException {

		User user = userRepository.findUserByUsername(username);

		if (user == null || !bCryptService.verify(password, user.getPassword()))
			throw new InvalidCredentialException("wrong username/password");

		return user;
	}

	@Override
	@Transactional
	public void delete(Long userId) throws EntityNotFoundException {
		
		User user = findUserChecked(userId);
		user.setDeleted(true);
		userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUser(Long userId) throws EntityNotFoundException {
		
		return findUserChecked(userId);
	}

	private User findUserChecked(Long userId) throws EntityNotFoundException {
		
		return userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Could not find user with id: " + userId));
	}

}
