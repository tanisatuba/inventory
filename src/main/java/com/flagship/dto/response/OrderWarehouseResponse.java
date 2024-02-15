package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;
@Data
@Builder
public class OrderWarehouseResponse {
  private Integer code;
  private List<SingleOrderWarehouse> allWarehouse;

  public static OrderWarehouseResponse from(List<SingleOrderWarehouse> allWarehouse) {
    return OrderWarehouseResponse.builder()
            .code(HttpStatus.SC_OK)
            .allWarehouse(allWarehouse)
            .build();
  }
}
