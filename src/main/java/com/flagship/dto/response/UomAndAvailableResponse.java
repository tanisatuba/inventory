package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class UomAndAvailableResponse {
  private Integer code;
  private UOM uom;
  private Double available;

  public static UomAndAvailableResponse from(UOM uom, Double available) {
    return UomAndAvailableResponse.builder()
            .code(HttpStatus.SC_OK)
            .uom(uom)
            .available(available)
            .build();
  }
}
