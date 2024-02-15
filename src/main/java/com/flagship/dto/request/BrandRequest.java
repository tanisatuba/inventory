package com.flagship.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class BrandRequest {
  @Valid
  @NotEmpty
  private String brandId;
  @Valid
  @NotEmpty
  private String brandName;
  @Valid
  @NotEmpty
  private String user;
}
