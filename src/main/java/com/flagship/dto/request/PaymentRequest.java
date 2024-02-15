package com.flagship.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class PaymentRequest {
  @Valid
  @NotNull
  private Long orderId;
  @Valid
  @NotNull
  private Double payment;
}
