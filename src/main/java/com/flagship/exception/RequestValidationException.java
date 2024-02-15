package com.flagship.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestValidationException extends RuntimeException {
  public RequestValidationException(String message) {
    super(message);
  }
}
