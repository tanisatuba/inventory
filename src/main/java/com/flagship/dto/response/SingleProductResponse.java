package com.flagship.dto.response;

import com.flagship.model.db.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleProductResponse {
  private String productId;
  private String productName;

  public static SingleProductResponse from(Product product) {
    return SingleProductResponse.builder()
            .productId(product.getProductId())
            .productName(product.getProductName())
            .build();
  }
}