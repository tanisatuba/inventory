package com.flagship.dto.request;

import com.flagship.constant.Regex;
import com.flagship.constant.enums.CustomerType;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.regex.Pattern;

@Data
public class CustomerRequest implements RequestValidator {
  @Valid
  @NotEmpty
  private String id;
  @Valid
  @NotEmpty
  private String name;
  private String company;
  @Valid
  @NotNull
  private CustomerType type;
  @Valid
  @NotEmpty
  private String address;
  private Integer creditTerm;
  @Valid
  @NotEmpty
  private String contact;
  private String binNo;
  private String supplier;
  @Valid
  @NotEmpty
  private String user;

  @Override
  public void validate() {
    if (!Pattern.matches(String.valueOf(Regex.MOBILE_NUMBER_REGEX), contact)) {
      throw new RequestValidationException("Phone Number Pattern is not Match.");
    }
    if (Objects.isNull(type)) {
      throw new RequestValidationException("Customer type should not be null. Customer Should be LOCAL " +
              "or CORPORATE");
    }
  }
}
