package com.flagship.dto.request;

import lombok.Data;

@Data
public class EditOrderRequest {
  private String product;
  private Double quantity;
  private Double total;
}
