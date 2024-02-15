package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;
@Data
@Builder
public class WarehouseResponse {
  private Integer code;
  private List<SingleWarehouse> allWarehouse;

  public static WarehouseResponse from(List<SingleWarehouse> allWarehouse) {
    return WarehouseResponse.builder()
            .code(HttpStatus.SC_OK)
            .allWarehouse(allWarehouse)
            .build();
  }
}
