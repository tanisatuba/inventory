package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class OrderRequisitionResponse {
  private Integer code;
  private List<RequisitionResponse> requisitionResponseList;
  private Double totalPiece;
  private String totalQuantity;

  public static OrderRequisitionResponse from(List<RequisitionResponse> requisitionResponses, Double totalKg, Double totalCartoon, Double totalPiece) {
    return OrderRequisitionResponse.builder()
            .code(HttpStatus.SC_OK)
            .requisitionResponseList(requisitionResponses)
            .totalPiece(totalPiece)
            .totalQuantity(totalCartoon + " ctn: " + totalKg + " kg")
            .build();
  }
}
