package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class SuccessReturnResponseUsingSerial {
  private Integer code;
  private List<ReturnResponseUsingSerial> returnResponseUsingSerials;
  public static SuccessReturnResponseUsingSerial from(List<ReturnResponseUsingSerial> returnResponseUsingSerials){
    return SuccessReturnResponseUsingSerial.builder()
            .code(HttpStatus.SC_OK)
            .returnResponseUsingSerials(returnResponseUsingSerials)
            .build();
  }
}
