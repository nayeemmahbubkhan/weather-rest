package de.creatision.weather.service;

import de.creatision.weather.domainobject.User;
import de.creatision.weather.exception.ConstraintsViolationException;
import de.creatision.weather.exception.EntityNotFoundException;
import de.creatision.weather.exception.InvalidCredentialException;
import de.creatision.weather.exception.UserAlreadyExistException;

public interface UserService {

	User signUp(User operation) throws ConstraintsViolationException, UserAlreadyExistException;

	public User login(String username, String password) throws InvalidCredentialException;

	public User findUser(Long userId) throws EntityNotFoundException;

	void delete(Long longId) throws EntityNotFoundException;
}
