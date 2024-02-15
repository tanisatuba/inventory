package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequisitionResponse {
  private String product;
  private Double piece;
  private String quantity;

  public static RequisitionResponse from(String product, Double piece, Double cartoon, Double kgLt) {
    return RequisitionResponse.builder()
            .product(product)
            .piece(piece)
            .quantity(cartoon + " ctn: " + kgLt + " kg")
            .build();
  }
}
