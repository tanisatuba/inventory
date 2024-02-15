package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllPendingOrdersResponse {
  private Integer code;
  private List<PendingOrderResponse> pendingOrderResponseList;

  public static AllPendingOrdersResponse from(List<PendingOrderResponse> pendingOrderResponseList) {
    return AllPendingOrdersResponse.builder()
            .code(HttpStatus.SC_OK)
            .pendingOrderResponseList(pendingOrderResponseList)
            .build();
  }
}
