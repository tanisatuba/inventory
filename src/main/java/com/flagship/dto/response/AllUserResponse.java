package com.flagship.dto.response;

import com.flagship.model.db.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllUserResponse {
  private Long userId;
  private String name;

  public static AllUserResponse from(User user) {
    return AllUserResponse.builder()
            .userId(user.getId())
            .name(user.getName())
            .build();
  }
}
