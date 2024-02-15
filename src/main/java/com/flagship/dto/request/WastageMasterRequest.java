package com.flagship.dto.request;

import com.flagship.constant.enums.Cause;
import com.flagship.constant.enums.UOM;
import com.flagship.constant.enums.Warehouse;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data
public class WastageMasterRequest implements RequestValidator {
  @Valid
  @NotNull
  private Warehouse warehouse;
  @Valid
  @NotEmpty
  private String user;
  @Valid
  @NotNull
  List<WastageDetailsRequest> wastageDetailsRequestList;

  @Override
  public void validate() {
    if (Objects.isNull(warehouse)) {
      throw new RequestValidationException("Warehouse should not be null");
    }
  }
}
