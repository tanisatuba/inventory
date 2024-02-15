package com.flagship.dto.request;

import com.flagship.constant.enums.Cause;
import com.flagship.constant.enums.UOM;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class WastageDetailsRequest implements RequestValidator {
  @Valid
  @NotNull
  private CommonRequest product;
  @Valid
  @NotNull
  private CommonRequest shipment;
  @Valid
  @NotNull
  private CommonRequest wastageFrom;
  @Valid
  @NotNull
  private UOM uom;
  @Valid
  @NotNull
  private Double quantity;
  @Valid
  @NotNull
  private Cause cause;

  @Override
  public void validate() {
    if (Objects.isNull(cause)) {
      throw new RequestValidationException("Cause should not be null");
    }
  }
}
