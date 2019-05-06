package de.creatision.weather.mapper;

import de.creatision.weather.datatransferobject.UserRegistrationRequest;
import de.creatision.weather.datatransferobject.UserResponse;
import de.creatision.weather.domainobject.User;

public class UserMapper {
	
	public static UserResponse makeUserResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.username(user.getUsername())
				.emailAddress(user.getEmailAddress())
				.createdAt(user.getCreatedAt())
				.build();
	}

	public static User makeUser(UserRegistrationRequest userRequest) {
		return User.builder()
				.firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName())
				.emailAddress(userRequest.getEmailAddress())
				.username(userRequest.getUsername())
				.password(userRequest.getPassword())
				.city(userRequest.getCity())
				.country(userRequest.getCountry())
				.build();
	}
    
}