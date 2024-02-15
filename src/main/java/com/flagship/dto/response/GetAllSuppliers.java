package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class GetAllSuppliers {
  private Integer code;
  private List<SingleSupplier> supplierList;

  public static GetAllSuppliers from(List<SingleSupplier> supplierList) {
    return GetAllSuppliers.builder()
            .code(HttpStatus.SC_OK)
            .supplierList(supplierList)
            .build();
  }
}
