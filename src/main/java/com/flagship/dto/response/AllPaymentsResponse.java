package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllPaymentsResponse {
  private Integer code;
  private List<SinglePaymentResponse> paymentResponseList;
  public static AllPaymentsResponse from(List<SinglePaymentResponse> paymentResponseList){
    return AllPaymentsResponse.builder()
            .code(HttpStatus.SC_OK)
            .paymentResponseList(paymentResponseList)
            .build();
  }
}
