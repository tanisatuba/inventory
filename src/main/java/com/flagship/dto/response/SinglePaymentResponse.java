package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SinglePaymentResponse {
  private String customerId;
  private String customer;
  private String company;
  private Double due;
  public static SinglePaymentResponse from(String customerId, String customer, String company, Double due){
    return SinglePaymentResponse.builder()
            .customerId(customerId)
            .customer(customer)
            .company(company)
            .due(due)
            .build();
  }
}
