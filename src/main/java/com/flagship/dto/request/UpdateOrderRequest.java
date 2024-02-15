package com.flagship.dto.request;

import lombok.Data;

@Data
public class UpdateOrderRequest {
  private Long orderId;
  private String shipment;
  private String productId;
  private String warehouse;
}
