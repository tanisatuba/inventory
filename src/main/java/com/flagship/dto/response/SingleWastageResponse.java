package com.flagship.dto.response;

import com.flagship.constant.enums.Cause;
import com.flagship.model.db.Wastage;
import lombok.Builder;
import lombok.Data;

import javax.sound.midi.Soundbank;

@Data
@Builder
public class SingleWastageResponse {
  private String product;
  private Double cartoon;
  private Double piece;
  private Double kgLt;
  private Cause cause;
  public static SingleWastageResponse from(Wastage wastage){
    return SingleWastageResponse.builder()
            .product(wastage.getProduct().getProductName())
            .cartoon(wastage.getCartoon())
            .piece(wastage.getPiece())
            .kgLt(wastage.getKgLt())
            .cause(wastage.getCause())
            .build();
  }
}
