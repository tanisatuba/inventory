package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class GetUomResponse {
  private Integer code;
  private UOM uom;
  public static GetUomResponse from(UOM uom){
    return GetUomResponse.builder()
            .code(HttpStatus.SC_OK)
            .uom(uom)
            .build();
  }
}
