package com.flagship.dto.response;

import com.flagship.model.db.Supplier;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleSupplier {
  private String supplierId;
  private String supplierName;

  public static SingleSupplier from(Supplier supplier) {
    return SingleSupplier.builder()
            .supplierId(supplier.getSupplierId())
            .supplierName(supplier.getSupplierName())
            .build();
  }
}
