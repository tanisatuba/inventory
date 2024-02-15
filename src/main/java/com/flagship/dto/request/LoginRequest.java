package com.flagship.dto.request;

import com.flagship.dto.RequestValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


@Data
public class LoginRequest implements RequestValidator {
  @Valid
  @NotEmpty
  private String email;
  @Valid
  @NotEmpty
  private String password;

  @Override
  public void validate() {

  }
}
