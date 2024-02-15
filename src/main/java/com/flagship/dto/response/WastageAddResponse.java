package com.flagship.dto.response;

import com.flagship.constant.enums.Cause;
import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.User;
import com.flagship.model.db.Wastage;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class WastageAddResponse {
  private Integer code;
  private String message;
  private Warehouse warehouse;
  private String user;
  private List<WastageResponse> wastageResponseList;

  public static WastageAddResponse from(String message, Warehouse warehouse, String user, List<WastageResponse> wastageResponseList) {
    return WastageAddResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .warehouse(warehouse)
            .user(user)
            .wastageResponseList(wastageResponseList)
            .build();
  }
}
