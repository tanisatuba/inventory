package com.flagship.dto.response;

import com.flagship.model.db.Sale;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class SaleResponse {
  private Integer code;
  private String message;
  private String supplier;
  private String product;
  private String saleCode;

  public static SaleResponse from(String message, Sale sale) {
    return SaleResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .supplier(sale.getSupplier().getSupplierName())
            .product(sale.getProduct().getProductName())
            .saleCode(sale.getSaleCode())
            .build();
  }
}
