package de.weather.service.security;

import de.weather.exception.DataFormatException;
import de.weather.exception.InvalidAccessTokenException;

public interface JWTService {

  String encodeIntoJwt(String userId);

  String decodeAndVerifyJwt(String jwt) throws SecurityException, 
  DataFormatException, InvalidAccessTokenException;

}