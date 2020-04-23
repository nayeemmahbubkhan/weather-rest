package de.weather.restcontroller;

import static de.weather.mapper.UserMapper.makeUser;
import static de.weather.mapper.UserMapper.makeUserResponse;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.weather.datatransferobject.UserLoginRequest;
import de.weather.datatransferobject.UserResponse;
import de.weather.datatransferobject.UserSignUpRequest;
import de.weather.domainobject.User;
import de.weather.exception.ConstraintsViolationException;
import de.weather.exception.InvalidCredentialException;
import de.weather.exception.UserAlreadyExistException;
import de.weather.service.UserService;
import de.weather.service.security.JWTService;


/**
 * All operations with a user will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/users")
public class UserController {

	private UserService userService;
	private JWTService securityService;

	@Autowired
	public UserController(UserService userService, JWTService securityService) {
		this.userService = userService;
		this.securityService = securityService;
	}

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse signUp(@RequestBody @Valid UserSignUpRequest userRequest, HttpServletResponse response)
			throws ConstraintsViolationException, UserAlreadyExistException {

		User userDO = makeUser(userRequest);
		UserResponse user = makeUserResponse(userService.signUp(userDO));
		String accessToken = securityService.encodeIntoJwt(Long.toString(user.getId()));
		response.setHeader("access-token", accessToken);
		return user;
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse login(@RequestBody @Valid UserLoginRequest userRequest, HttpServletResponse response)
			throws InvalidCredentialException {

		UserResponse user = makeUserResponse(userService.login(userRequest.getUsername(), userRequest.getPassword()));
		String accessToken = securityService.encodeIntoJwt(Long.toString(user.getId()));
		response.setHeader("access-token", accessToken);        
		return user;
	}
	
	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse logout(@RequestHeader String accessToken, HttpServletResponse response)
			throws InvalidCredentialException {
      
		return null;
	}

}
