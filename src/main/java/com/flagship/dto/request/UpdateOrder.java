package com.flagship.dto.request;

import com.flagship.constant.enums.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class UpdateOrder {
  private Warehouse warehouse;
  private String deliveryMan;
  private String user;
  private List<UpdateOrderRequest> updateOrderRequestList;
}
