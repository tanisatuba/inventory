package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;
@Data
@Builder
public class GetUomAndAvailableResponse {
  private Integer code;
  private UOM uom;
  private Double available;
  public static GetUomAndAvailableResponse from(UOM uom, Double available){
    return GetUomAndAvailableResponse.builder()
            .code(HttpStatus.SC_OK)
            .uom(uom)
            .available(available)
            .build();
  }
}
