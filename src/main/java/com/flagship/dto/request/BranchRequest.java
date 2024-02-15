package com.flagship.dto.request;

import com.flagship.dto.RequestValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BranchRequest implements RequestValidator {
  @Valid
  @NotNull
  private CommonRequest supplier;
  @Valid
  @NotEmpty
  private String name;
  @Valid
  @NotEmpty
  private String address;
  @Valid
  @NotEmpty
  private String user;

  @Override
  public void validate() {

  }
}
