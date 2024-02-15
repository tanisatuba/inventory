package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum OrderStatus {
  PENDING(1),
  DELIVERED(2),
  WAITING(3),
  ;

  private final int type;

  OrderStatus(int type) {
    this.type = type;
  }

  public static OrderStatus fromValue(int value) {
    switch (value) {
      case 1:
        return PENDING;
      case 2:
        return DELIVERED;
      case 3:
        return WAITING;
      default:
        return null;
    }
  }

  @JsonCreator
  public static OrderStatus fromName(String name) {
    switch (StringUtils.upperCase(name)) {
      case "PENDING":
        return PENDING;
      case "DELIVERED":
        return DELIVERED;
      case "WAITING":
        return WAITING;
      default:
        return null;
    }
  }

  @JsonValue
  public String getName() {
    return toString();
  }
}
