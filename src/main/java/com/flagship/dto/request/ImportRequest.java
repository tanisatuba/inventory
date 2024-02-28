package com.flagship.dto.request;

import com.flagship.constant.Regex;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@Setter
public class ImportRequest implements RequestValidator {
  @Valid
  @NotEmpty
  private String shipmentNo;
  @Valid
  @NotNull
  private CommonRequest country;
  @Valid
  @NotEmpty
  private String date;
  @Valid
  @NotEmpty
  private String user;
  @Valid
  @NotNull
  List<ImportDetailsRequest> importDetailsRequestList;

  @Override
  public void validate() {
    if (!Pattern.matches(String.valueOf(Regex.DATE_REGEX), date)) {
      throw new RequestValidationException("Date Should be dd-MM-yyyy(01-01-1990)");
    }
  }
}
