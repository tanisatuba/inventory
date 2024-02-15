package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllCountryResponse {
  private Integer code;
  private List<SingleCountryResponse> countryResponseList;

  public static AllCountryResponse from(List<SingleCountryResponse> singleCountryResponseList) {
    return AllCountryResponse.builder()
            .code(HttpStatus.SC_OK)
            .countryResponseList(singleCountryResponseList)
            .build();
  }
}
