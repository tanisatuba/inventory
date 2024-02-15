package com.flagship.dto.response;

import com.flagship.constant.enums.Cause;
import com.flagship.model.db.Returns;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleReturnResponse {
  private String product;
  private Double piece;
  private String quantity;
  private Cause cause;
  public static SingleReturnResponse from(Returns returns) {
    return SingleReturnResponse.builder()
            .product(returns.getProduct().getProductName())
            .piece(returns.getPiece())
            .quantity(returns.getCartoon() + " ctn: " + returns.getKgLt() + " kg")
            .cause(returns.getCause())
            .build();
  }
}
