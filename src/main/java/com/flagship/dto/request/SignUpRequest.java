package com.flagship.dto.request;

import com.flagship.constant.Regex;
import com.flagship.constant.enums.Gender;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.regex.Pattern;

@Data
public class SignUpRequest implements RequestValidator {
  @Valid
  @NotEmpty
  private String name;
  @Valid
  @NotEmpty
  private String email;
  @Valid
  @NotEmpty
  private String dateOfBirth;
  @Valid
  @NotEmpty
  private String password;
  @Valid
  @NotEmpty
  private String confirmPassword;
  @Valid
  @NotEmpty
  private String superPassword;
  @Valid
  private Gender gender;

  @Override
  public void validate() {
    if (Objects.isNull(gender)) {
      throw new RequestValidationException("Gender should not be null. Gender Should be MALE or FEMALE");
    }
    if (!Pattern.matches(String.valueOf(Regex.EMAIL_REGEX), email)) {
      throw new RequestValidationException("Email Pattern is not Match.");
    }
    if (!Pattern.matches(String.valueOf(Regex.DATE_REGEX), dateOfBirth)) {
      throw new RequestValidationException("Date of Birth Should be yyyy-MM-dd(1900-01-01)");
    }
    if (!password.equals(confirmPassword)) {
      throw new RequestValidationException("Password and Confirm Password is not match");
    }
    if (!superPassword.equals(Regex.SUPER_PASSWORD)) {
      throw new RequestValidationException("Super Password is not match");
    }
  }
}
