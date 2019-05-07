package de.creatision.weather.restcontroller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.creatision.weather.datatransferobject.UserLoginRequest;
import de.creatision.weather.datatransferobject.UserSignUpRequest;
import de.creatision.weather.datatransferobject.UserResponse;
import de.creatision.weather.domainobject.User;
import de.creatision.weather.exception.ConstraintsViolationException;
import de.creatision.weather.exception.InvalidCredentialException;
import de.creatision.weather.exception.UserAlreadyExistException;
import de.creatision.weather.service.UserService;
import de.creatision.weather.service.security.JWTService;

import static de.creatision.weather.mapper.UserMapper.makeUser;
import static de.creatision.weather.mapper.UserMapper.makeUserResponse;;

/**
 * All operations with a user plan will be routed by this controller.
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
	public UserResponse signUp(@RequestBody UserSignUpRequest userRequest, HttpServletResponse response)
			throws ConstraintsViolationException, UserAlreadyExistException {

		User userDO = makeUser(userRequest);
		UserResponse user = makeUserResponse(userService.signUp(userDO));
		String accessToken = securityService.encodeIntoJwt(Long.toString(user.getId()));
		response.setHeader("access-token", accessToken);
        //FIXME: fix on react FE, so that it can read from response header
		user.setAccessToken(accessToken);
		return user;
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse login(@RequestBody UserLoginRequest userRequest, HttpServletResponse response)
			throws InvalidCredentialException {

		UserResponse user = makeUserResponse(userService.login(userRequest.getUsername(), userRequest.getPassword()));
		String accessToken = securityService.encodeIntoJwt(Long.toString(user.getId()));
		response.setHeader("access-token", accessToken);
	    //FIXME: fix on react FE, so that it can read from response header
		user.setAccessToken(accessToken);
        
		return user;
	}

}
