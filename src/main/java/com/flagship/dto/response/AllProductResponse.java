package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllProductResponse {
  private Integer code;
  private List<SingleProductResponse> allProductResponse;

  public static AllProductResponse from(List<SingleProductResponse> allProduct) {
    return AllProductResponse.builder()
            .code(HttpStatus.SC_OK)
            .allProductResponse(allProduct)
            .build();
  }
}
