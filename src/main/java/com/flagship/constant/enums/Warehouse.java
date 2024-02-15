package com.flagship.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum Warehouse {
  SIKAJU_COLD_STORAGE(1),
  BADC_COLD_STORAGE(2),
  Z_INNOVATIVE_TECHNOLOGYS(3),
  AZOMPUR_WAREHOUSE(4),
  BG_FOOD_AND_AGRO_LTD(5),
  ;

  private final int type;

  Warehouse(int type) {
    this.type = type;
  }

  public static Warehouse fromValue(int value) {
    switch (value) {
      case 1:
        return SIKAJU_COLD_STORAGE;
      case 2:
        return BADC_COLD_STORAGE;
      case 3:
        return Z_INNOVATIVE_TECHNOLOGYS;
      case 4:
        return AZOMPUR_WAREHOUSE;
      case 5:
        return BG_FOOD_AND_AGRO_LTD;
      default:
        return null;
    }
  }

  @JsonCreator
  public static Warehouse fromName(String name) {
    switch (StringUtils.upperCase(name)) {
      case "SIKAJU_COLD_STORAGE":
        return SIKAJU_COLD_STORAGE;
      case "BADC_COLD_STORAGE":
        return BADC_COLD_STORAGE;
      case "Z_INNOVATIVE_TECHNOLOGYS":
        return Z_INNOVATIVE_TECHNOLOGYS;
      case "AZOMPUR_WAREHOUSE":
        return AZOMPUR_WAREHOUSE;
      case "BG_FOOD_AND_AGRO_LTD":
        return BG_FOOD_AND_AGRO_LTD;
      default:
        return null;
    }
  }

  @JsonValue
  public String getName() {
    return toString();
  }
}
