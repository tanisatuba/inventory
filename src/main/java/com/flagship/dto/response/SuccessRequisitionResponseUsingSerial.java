package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class SuccessRequisitionResponseUsingSerial {
  private Integer code;
  private List<RequisitionResponseUsingSerial> requisitionResponseUsingSerials;

  public static SuccessRequisitionResponseUsingSerial from(List<RequisitionResponseUsingSerial> requisitionResponseUsingSerials) {
    return SuccessRequisitionResponseUsingSerial.builder()
            .code(HttpStatus.SC_OK)
            .requisitionResponseUsingSerials(requisitionResponseUsingSerials)
            .build();
  }
}
