package com.flagship.dto.request;

import com.flagship.constant.Regex;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.regex.Pattern;

@Data
public class SalesPersonRequest implements RequestValidator {
  @Valid
  @NotEmpty
  private String salesPersonId;
  @Valid
  @NotEmpty
  private String salesPersonName;
  @Valid
  @NotEmpty
  private String phoneNumber;
  @Valid
  private String area;
  @Valid
  @NotEmpty
  private String user;

  @Override
  public void validate() {
    if (!Pattern.matches(String.valueOf(Regex.MOBILE_NUMBER_REGEX), phoneNumber)) {
      throw new RequestValidationException("Phone Number Pattern is not Match.");
    }
  }
}
