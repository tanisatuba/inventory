package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllBillsResponse {
  private Integer code;
  private List<SingleBillResponse> billsList;

  public static AllBillsResponse from(List<SingleBillResponse> billsList) {
    return AllBillsResponse.builder()
            .code(HttpStatus.SC_OK)
            .billsList(billsList)
            .build();
  }
}
