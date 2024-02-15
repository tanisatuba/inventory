package com.flagship.dto.request;

import com.flagship.constant.enums.Warehouse;
import lombok.Data;

@Data
public class MoveRequest {
  private CommonRequest product;
  private CommonRequest shipment;
  private CommonRequest moveFrom;
  private Warehouse moveTo;
  private Double quantity;
  private String user;
}
