package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllBrandResponse {
  private Integer code;
  private List<SingleBrandResponse> brandResponseList;

  public static AllBrandResponse from(List<SingleBrandResponse> singleBrandResponses) {
    return AllBrandResponse.builder()
            .code(HttpStatus.SC_OK)
            .brandResponseList(singleBrandResponses)
            .build();
  }
}
