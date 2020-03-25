package de.weather.service;

import de.weather.domainobject.User;
import de.weather.exception.ConstraintsViolationException;
import de.weather.exception.EntityNotFoundException;
import de.weather.exception.InvalidCredentialException;
import de.weather.exception.UserAlreadyExistException;

public interface UserService {

	User signUp(User operation) throws ConstraintsViolationException, UserAlreadyExistException;

	public User login(String username, String password) throws InvalidCredentialException;

	public User findUser(Long userId) throws EntityNotFoundException;

	void delete(Long longId) throws EntityNotFoundException;
}
