package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum CustomerType {
  LOCAL(1),
  CORPORATE(2),
  ;

  private final int type;

  CustomerType(int type) {
    this.type = type;
  }

  public static CustomerType fromValue(int value) {
    switch (value) {
      case 1:
        return LOCAL;
      case 2:
        return CORPORATE;
      default:
        return null;
    }
  }

  @JsonCreator
  public static CustomerType fromName(String name) {
    switch (StringUtils.upperCase(name)) {
      case "LOCAL":
        return LOCAL;
      case "CORPORATE":
        return CORPORATE;
      default:
        return null;
    }
  }

  @JsonValue
  public String getName() {
    return toString();
  }
}
