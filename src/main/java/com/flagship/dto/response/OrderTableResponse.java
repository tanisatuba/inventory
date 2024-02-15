package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class OrderTableResponse {
  private Integer code;
  private List<SingleOrderTableResponse> singleOrderTableResponses;

  public static OrderTableResponse from(List<SingleOrderTableResponse> orderTableResponses) {
    return OrderTableResponse.builder()
            .code(HttpStatus.SC_OK)
            .singleOrderTableResponses(orderTableResponses)
            .build();
  }
}
