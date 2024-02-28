package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class DeleteResponse {
  private Integer code;
  public static DeleteResponse from(){
    return DeleteResponse.builder()
            .code(HttpStatus.SC_OK)
            .build();
  }
}
