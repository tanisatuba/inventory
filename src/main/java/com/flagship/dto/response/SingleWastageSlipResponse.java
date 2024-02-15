package com.flagship.dto.response;

import com.flagship.constant.enums.Cause;
import com.flagship.model.db.Returns;
import com.flagship.model.db.Wastage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleWastageSlipResponse {
  private String product;
  private Double piece;
  private String quantity;
  private Cause cause;

  public static SingleWastageSlipResponse from(Wastage wastage) {
    return SingleWastageSlipResponse.builder()
            .product(wastage.getProduct().getProductName())
            .piece(wastage.getPiece())
            .quantity(wastage.getCartoon() + " ctn: " + wastage.getKgLt() + " kg")
            .cause(wastage.getCause())
            .build();
  }
}
