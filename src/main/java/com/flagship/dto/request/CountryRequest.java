package com.flagship.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class CountryRequest {
  @Valid
  @NotEmpty
  private String countryId;
  @Valid
  @NotEmpty
  private String countryName;
  @Valid
  @NotEmpty
  private String user;
}
