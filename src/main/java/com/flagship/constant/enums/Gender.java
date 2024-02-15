package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum Gender {
  MALE(1),
  FEMALE(2),
  ;

  private final int type;

  Gender(int type) {
    this.type = type;
  }

  public static Gender fromValue(int value) {
    switch (value) {
      case 1:
        return MALE;
      case 2:
        return FEMALE;
      default:
        return null;
    }
  }

  @JsonCreator
  public static Gender fromName(String name) {
    switch (StringUtils.upperCase(name)) {
      case "MALE":
        return MALE;
      case "FEMALE":
        return FEMALE;
      default:
        return null;
    }
  }

  @JsonValue
  public String getName() {
    return toString();
  }

}
