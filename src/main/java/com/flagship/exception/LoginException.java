package com.flagship.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginException extends RuntimeException {
  public LoginException(String message) {
    super(message);
  }
}
