package com.flagship.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExistException extends RuntimeException {
  private final Integer code;

  public UserExistException(Integer code, String message) {
    super(message);
    this.code = code;
  }
}
