package com.flagship.dto.response;

import com.flagship.model.db.Supplier;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class SupplierResponse {
  private Integer code;
  private String message;
  private String supplierId;
  private String supplierName;

  public static SupplierResponse from(String message, Supplier supplier) {
    return SupplierResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .supplierId(supplier.getSupplierId())
            .supplierName(supplier.getSupplierName())
            .build();
  }
}
