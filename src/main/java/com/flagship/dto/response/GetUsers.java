package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class GetUsers {
  private Integer code;
  private List<AllUserResponse> allUserResponseList;

  public static GetUsers from(List<AllUserResponse> allUserResponseList) {
    return GetUsers.builder()
            .code(HttpStatus.SC_OK)
            .allUserResponseList(allUserResponseList)
            .build();
  }
}
