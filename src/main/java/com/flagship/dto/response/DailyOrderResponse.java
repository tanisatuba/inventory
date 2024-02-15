package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class DailyOrderResponse {
  private Integer code;
  private List<DailyOrderProductsResponse> orderResponseList;
  private List<String> uniqueName;

  public static DailyOrderResponse from(List<DailyOrderProductsResponse> productsResponses, List<String> uniqueName) {
    return DailyOrderResponse.builder()
            .code(HttpStatus.SC_OK)
            .orderResponseList(productsResponses)
            .uniqueName(uniqueName)
            .build();
  }
}
