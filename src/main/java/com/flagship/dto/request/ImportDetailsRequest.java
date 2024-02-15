package com.flagship.dto.request;

import com.flagship.constant.Regex;
import com.flagship.constant.enums.UOM;
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
public class ImportDetailsRequest implements RequestValidator {
  @Valid
  @NotNull
  private CommonRequest product;
  @Valid
  @NotNull
  private CommonRequest category;
  @Valid
  @NotNull
  private CommonRequest brand;
  private String production;
  @Valid
  @NotNull
  private CommonRequest importCountry;
  @Valid
  private Warehouse warehouse;
  private String expire;
  @Valid
  @NotNull
  private Double cartoon;
  @Valid
  @NotNull
  private Double piece;
  @Valid
  @NotNull
  private Double kgLt;
  @Valid
  private UOM uom;
  @Valid
  @NotNull
  private Double price;
  @Valid
  @NotNull
  private Double total;

  @Override
  public void validate() {
    if (Objects.isNull(uom)) {
      throw new RequestValidationException("UOM should not be null. UOM Should be CARTOON or KG_LT or PIECE");
    }
    if (Objects.isNull(warehouse)) {
      throw new RequestValidationException("Warehouse should not be null. Warehouse Should be SIKAJU_COLD_STORAGE" +
              " or BADC_COLD_STORAGE or Z_INNOVATIVE_TECHNOLOGYS or AZOMPUR_WAREHOUSE or BG_FOOD_AND_AGRO_LTD");
    }
    if ( production!= null && !production.isEmpty() && !Pattern.matches(String.valueOf(Regex.DATE_REGEX), production)) {
      throw new RequestValidationException("Production Should be yyyy-MM-dd(1900-01-01)");
    }
    if (expire!= null && !expire.isEmpty() && !Pattern.matches(String.valueOf(Regex.DATE_REGEX), expire)) {
      throw new RequestValidationException("Expire Should be yyyy-MM-dd(1900-01-01)");
    }
  }
}
