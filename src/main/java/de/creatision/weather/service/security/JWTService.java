package de.creatision.weather.service.security;

import de.creatision.weather.exception.DataFormatException;
import de.creatision.weather.exception.InvalidAccessTokenException;

public interface JWTService {

  String encodeIntoJwt(String userId);

  String decodeAndVerifyJwt(String jwt) throws SecurityException, 
  DataFormatException, InvalidAccessTokenException;

}