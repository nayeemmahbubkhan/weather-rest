package de.creatision.weather.service.security;

public interface BCryptService {
	
	String encode(String rawPassword);
	
	boolean verify(String rawPassword, String encodedPassword);

}
