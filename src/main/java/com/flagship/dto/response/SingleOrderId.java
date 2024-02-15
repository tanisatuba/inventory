package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleOrderId {
  private Long orderId;
  public static SingleOrderId from(Long orderId){
    return SingleOrderId.builder()
            .orderId(orderId)
            .build();
  }
}
