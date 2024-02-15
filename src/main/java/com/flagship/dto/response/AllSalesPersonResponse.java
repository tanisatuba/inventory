package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllSalesPersonResponse {
  private Integer code;
  private List<SingleSalesPersonResponse> singleSalesPersonResponseList;

  public static AllSalesPersonResponse from(List<SingleSalesPersonResponse> singleSalesPersonResponseList) {
    return AllSalesPersonResponse.builder()
            .code(HttpStatus.SC_OK)
            .singleSalesPersonResponseList(singleSalesPersonResponseList)
            .build();
  }
}
