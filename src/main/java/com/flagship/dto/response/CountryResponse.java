package com.flagship.dto.response;

import com.flagship.model.db.Country;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class CountryResponse {
  private Integer code;
  private String message;
  private String countryId;
  private String countryName;

  public static CountryResponse from(String message, Country country) {
    return CountryResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .countryId(country.getCountryId())
            .countryName(country.getCountryName())
            .build();
  }
}
