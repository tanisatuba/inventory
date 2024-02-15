package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllImportResponse {
  private Integer code;
  private List<SingleImportResponse> importResponseList;

  public static AllImportResponse from(List<SingleImportResponse> importResponseList) {
    return AllImportResponse.builder()
            .code(HttpStatus.SC_OK)
            .importResponseList(importResponseList)
            .build();
  }
}
