package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllProductRevenueResponse {
  private Integer code;
  private Double total;
  private List<SingleProductRevenueResponse> revenueResponses;

  public static AllProductRevenueResponse from(List<SingleProductRevenueResponse> revenueResponses, Double total) {
    return AllProductRevenueResponse.builder()
            .code(HttpStatus.SC_OK)
            .total(total)
            .revenueResponses(revenueResponses)
            .build();
  }
}
