package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Builder
@Data
public class SignUpResponse {
  private Integer code;
  private String message;

  public static SignUpResponse from(String message) {
    return SignUpResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .build();
  }
}
