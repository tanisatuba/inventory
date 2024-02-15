package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class EditOrderResponse {
  private Integer code;
  private String message;

  public static EditOrderResponse from() {
    return EditOrderResponse.builder()
            .code(HttpStatus.SC_OK)
            .message("Order Edited Successfully")
            .build();
  }
}
