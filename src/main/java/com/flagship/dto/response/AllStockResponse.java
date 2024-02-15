package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllStockResponse {
  private Integer code;
  private List<StockResponse> stockResponseList;

  public static AllStockResponse from(List<StockResponse> stockResponseList) {
    return AllStockResponse.builder()
            .code(HttpStatus.SC_OK)
            .stockResponseList(stockResponseList)
            .build();
  }
}
