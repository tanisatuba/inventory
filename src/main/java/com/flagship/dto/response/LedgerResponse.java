package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class LedgerResponse {
  private Integer code;
  private List<AllLedgerResponse> ledgerResponses;
  public static LedgerResponse from(List<AllLedgerResponse> ledgerResponses){
    return LedgerResponse.builder()
            .code(HttpStatus.SC_OK)
            .ledgerResponses(ledgerResponses)
            .build();
  }
}
