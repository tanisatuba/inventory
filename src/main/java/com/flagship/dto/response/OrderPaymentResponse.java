package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class OrderPaymentResponse {
  private Integer code;
  private String message;
  public static OrderPaymentResponse from(){
    return OrderPaymentResponse.builder()
            .code(HttpStatus.SC_OK)
            .message("Payment successful")
            .build();
  }
}
