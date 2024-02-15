package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum Cause {
  DAMAGED(1),
  DATE_OVER(2),
  ;

  private final int type;

  Cause(int type) {
    this.type = type;
  }

  public static Cause fromValue(int value) {
    switch (value) {
      case 1:
        return DAMAGED;
      case 2:
        return DATE_OVER;
      default:
        return null;
    }
  }

  @JsonCreator
  public static Cause fromName(String name) {
    switch (StringUtils.upperCase(name)) {
      case "DAMAGED":
        return DAMAGED;
      case "DATE_OVER":
        return DATE_OVER;
      default:
        return null;
    }
  }

  @JsonValue
  public String getName() {
    return toString();
  }

}
