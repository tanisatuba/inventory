package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class SuccessWastageResponseUsingSerial {
  private Integer code;
  private List<WastageResponseUsingSerial> wastageResponseUsingSerials;

  public static SuccessWastageResponseUsingSerial from(List<WastageResponseUsingSerial> wastageResponseUsingSerials) {
    return SuccessWastageResponseUsingSerial.builder()
            .code(HttpStatus.SC_OK)
            .wastageResponseUsingSerials(wastageResponseUsingSerials)
            .build();
  }
}
