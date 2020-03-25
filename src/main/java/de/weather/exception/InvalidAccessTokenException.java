package de.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAccessTokenException extends Exception {

  private static final long serialVersionUID = 1L;

  public InvalidAccessTokenException(String message, Exception e) {
    super(message, e);
  }
  
  public InvalidAccessTokenException(String message) {
    super(message);
  }

}