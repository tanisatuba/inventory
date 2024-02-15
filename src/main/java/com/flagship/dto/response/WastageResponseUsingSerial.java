package com.flagship.dto.response;

import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.Wastage;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WastageResponseUsingSerial {
  private Long serialNo;
  private String name;
  private Warehouse warehouse;
  private String createdOn;

  public static WastageResponseUsingSerial from(Wastage wastage) {
    return WastageResponseUsingSerial.builder()
            .name(wastage.getCreatedBy().getName())
            .warehouse(wastage.getWarehouse())
            .createdOn(DateUtil.getFormattedDate(wastage.getCreatedOn()))
            .serialNo(wastage.getSerialNo())
            .build();
  }
}
