package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum BillStatus {
  PAID(1),
  UNPAID(2),
  PARTIALLY_PAID(3),
  ;

  private final int type;

  BillStatus(int type) {
    this.type = type;
  }

  public static BillStatus fromValue(int value) {
    switch (value) {
      case 1:
        return PAID;
      case 2:
        return UNPAID;
      case 3:
        return PARTIALLY_PAID;
      default:
        return null;
    }
  }

  @JsonCreator
  public static BillStatus fromName(String name) {
    switch (StringUtils.upperCase(name)) {
      case "PAID":
        return PAID;
      case "UNPAID":
        return UNPAID;
      case "PARTIALLY_PAID":
        return PARTIALLY_PAID;
      default:
        return null;
    }
  }

  @JsonValue
  public String getName() {
    return toString();
  }
}
