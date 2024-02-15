package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ShipmentResponse {
  private Integer code;
  private List<SingleShipment> allShipment;

  public static ShipmentResponse from(List<SingleShipment> singleShipments) {
    return ShipmentResponse.builder()
            .code(HttpStatus.SC_OK)
            .allShipment(singleShipments)
            .build();
  }
}
