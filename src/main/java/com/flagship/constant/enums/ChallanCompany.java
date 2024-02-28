package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum ChallanCompany {
  TANISA_INTERNATIONAL_LTD(1),
  FLAGSHIP_INTERNATIONAL_LTD(2),
  ;

  private final int type;

  ChallanCompany(int type) {
    this.type = type;
  }

  public static ChallanCompany fromValue(int value) {
    switch (value) {
      case 1:
        return TANISA_INTERNATIONAL_LTD;
      case 2:
        return FLAGSHIP_INTERNATIONAL_LTD;
      default:
        return null;
    }
  }

  @JsonCreator
  public static ChallanCompany fromName(String name) {
    switch (StringUtils.upperCase(name)) {
      case "TANISA_INTERNATIONAL_LTD":
        return TANISA_INTERNATIONAL_LTD;
      case "FLAGSHIP_INTERNATIONAL_LTD":
        return FLAGSHIP_INTERNATIONAL_LTD;
      default:
        return null;
    }
  }

  @JsonValue
  public String getName() {
    return toString();
  }
}
