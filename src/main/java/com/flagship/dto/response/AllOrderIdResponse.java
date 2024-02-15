package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllOrderIdResponse {
  private Integer code;
  private List<SingleOrderId> idList;
  public static AllOrderIdResponse from(List<SingleOrderId> idList){
    return AllOrderIdResponse.builder()
            .code(HttpStatus.SC_OK)
            .idList(idList)
            .build();
  }
}
