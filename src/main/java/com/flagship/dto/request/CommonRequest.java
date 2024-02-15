package com.flagship.dto.request;

import com.flagship.dto.RequestValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class CommonRequest implements RequestValidator {
  @Valid
  @NotEmpty
  private String label;
  @Valid
  @NotEmpty
  private String value;

  @Override
  public void validate() {

  }
}
