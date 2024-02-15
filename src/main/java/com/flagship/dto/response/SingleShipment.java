package com.flagship.dto.response;

import com.flagship.model.db.ImportDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleShipment {
  private String shipmentNo;

  public static SingleShipment from(ImportDetails details) {
    return SingleShipment.builder()
            .shipmentNo(details.getImportMaster().getShipmentNo())
            .build();
  }
}
