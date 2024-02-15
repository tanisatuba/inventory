package com.flagship.dto.response;

import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.Requisition;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequisitionResponseUsingSerial {
  private Long serialNo;
  private String name;
  private Warehouse warehouse;
  private String createdOn;

  public static RequisitionResponseUsingSerial from(Requisition requisition) {
    return RequisitionResponseUsingSerial.builder()
            .name(requisition.getDeliveryMan())
            .warehouse(requisition.getWarehouse())
            .createdOn(DateUtil.getFormattedDate(requisition.getCreatedOn()))
            .serialNo(requisition.getSerialNo())
            .build();
  }
}
