package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class LastPriceResponse {
  private Integer code;
  private Double price;

  public static LastPriceResponse from(Double price) {
    return LastPriceResponse.builder()
            .code(HttpStatus.SC_OK)
            .price(price)
            .build();
  }
}
