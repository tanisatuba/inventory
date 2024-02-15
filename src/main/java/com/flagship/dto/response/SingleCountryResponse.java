package com.flagship.dto.response;

import com.flagship.model.db.Country;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleCountryResponse {
  private String countryId;
  private String countryName;

  public static SingleCountryResponse from(Country country) {
    return SingleCountryResponse.builder()
            .countryId(country.getCountryId())
            .countryName(country.getCountryName())
            .build();
  }
}
