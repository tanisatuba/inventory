package com.flagship.dto.request;

import com.flagship.constant.Regex;
import com.flagship.constant.enums.Warehouse;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.regex.Pattern;

@Data
public class MoveProductRequest implements RequestValidator {
  @Valid
  @NotEmpty
  private String importDate;
  @Valid
  @NotEmpty
  private String productId;
  @Valid
  @NotEmpty
  private String productName;
  @Valid
  @NotNull
  private Integer qtyCtn;
  @Valid
  @NotNull
  private Double qtyPiece;
  @Valid
  @NotNull
  private Double wgtPiece;
  @Valid
  @NotNull
  private Double wgtCtn;
  @Valid
  private Warehouse moveFrom;
  @Valid
  private Warehouse moveTo;

  @Override
  public void validate() {
    if (Objects.isNull(moveFrom)) {
      throw new RequestValidationException("Move From should not be null");
    }
    if (Objects.isNull(moveTo)) {
      throw new RequestValidationException("Move To should not be null");
    }
    if (!Pattern.matches(String.valueOf(Regex.DATE_REGEX), importDate)) {
      throw new RequestValidationException("Date of Birth Should be yyyy-MM-dd(1900-01-01)");
    }
  }
}
