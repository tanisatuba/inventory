package com.flagship.dto.response;

import com.flagship.model.db.Product;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Builder
@Data
public class ProductResponse {
  private Integer code;
  private String message;
  private String productId;
  private String productName;
  private String userEmail;

  public static ProductResponse from(String message, Product product) {
    return ProductResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .productId(product.getProductId())
            .productName(product.getProductName())
            .userEmail(product.getCreatedBy().getEmail())
            .build();
  }
}
