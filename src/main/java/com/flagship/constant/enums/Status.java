package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
@Getter
public enum Status {
  ACTIVE(1),
  INACTIVE(2),
  ;

  private final int type;

  Status(int type) {
    this.type = type;
  }

  public static Status fromValue(int value) {
    switch (value) {
      case 1:
        return ACTIVE;
      case 2:
        return INACTIVE;
      default:
        return null;
    }
  }

  @JsonCreator
  public static Status fromName(String name) {
    switch (StringUtils.upperCase(name)) {
      case "ACTIVE":
        return ACTIVE;
      case "INACTIVE":
        return INACTIVE;
      default:
        return null;
    }
  }

  @JsonValue
  public String getName() {
    return toString();
  }
}
