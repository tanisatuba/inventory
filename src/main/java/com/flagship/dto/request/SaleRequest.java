package com.flagship.dto.request;

import com.flagship.dto.RequestValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SaleRequest implements RequestValidator {
  @Valid
  @NotNull
  private CommonRequest supplier;
  @Valid
  @NotNull
  private CommonRequest product;
  @Valid
  @NotEmpty
  private String article;
  @Valid
  @NotEmpty
  private String saleCode;
  @Valid
  @NotEmpty
  private String user;

  @Override
  public void validate() {

  }
}
