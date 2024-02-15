package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Builder
@Data
public class LoginResponse {
  private Integer code;
  private String message;
  private String name;
  private String email;

  public static LoginResponse from(String message, String name, String email) {
    return LoginResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .name(name)
            .email(email)
            .build();
  }
}