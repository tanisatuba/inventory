package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

public enum UOM {
  KG(1),
  PIECE(2),
  CARTOON(3),
  LT(4),
  ;

  UOM(int type) {
  }

  public static UOM fromValue(int value) {
    switch (value) {
      case 1:
        return KG;
      case 2:
        return PIECE;
      case 3:
        return CARTOON;
      case 4:
        return LT;
      default:
        return null;
    }
  }

  @JsonCreator
  public static UOM fromName(String name) {
    switch (StringUtils.upperCase(name)) {
      case "KG":
        return KG;
      case "PIECE":
        return PIECE;
      case "CARTOON":
        return CARTOON;
      case "LT":
        return LT;
      default:
        return null;
    }
  }

  @JsonValue
  public String getName() {
    return toString();
  }
}
