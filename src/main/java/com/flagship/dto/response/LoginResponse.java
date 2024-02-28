package com.flagship.dto.response;

import com.flagship.model.db.User;
import com.flagship.util.DateUtil;
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
  private String createdAt;

  public static LoginResponse from(String message, User user) {
    return LoginResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .name(user.getName())
            .email(user.getEmail())
            .createdAt(DateUtil.getFormattedDate(user.getCreatedOn()))
            .build();
  }
}