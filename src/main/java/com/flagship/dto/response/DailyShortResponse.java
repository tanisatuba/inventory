package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyShortResponse {
  private String product;
  private Double stockOut;

  public static DailyShortResponse from(String product, Double stockOut) {
    return DailyShortResponse.builder()
            .product(product)
            .stockOut(stockOut)
            .build();
  }
}
