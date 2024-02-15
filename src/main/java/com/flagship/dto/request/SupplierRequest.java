package com.flagship.dto.request;

import com.flagship.dto.RequestValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class SupplierRequest implements RequestValidator {
  @Valid
  @NotEmpty
  private String supplierId;
  @Valid
  @NotEmpty
  private String supplierName;
  @Valid
  @NotEmpty
  private String user;

  @Override
  public void validate() {

  }
}
