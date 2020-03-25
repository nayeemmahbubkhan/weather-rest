package de.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidLocationException extends Exception {

  private static final long serialVersionUID = 1L;
  
  public InvalidLocationException(String message) {
    super(message);
  }

}