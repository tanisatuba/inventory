package com.flagship.dto.response;

import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.Returns;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReturnResponseUsingSerial {
  private Long serialNo;
  private String name;
  private Warehouse warehouse;
  private String createdOn;
  private String customer;
  private String branch;
  public static ReturnResponseUsingSerial from(Returns returns) {
    return ReturnResponseUsingSerial.builder()
            .name(returns.getDeliveryMan())
            .warehouse(returns.getWarehouse())
            .createdOn(DateUtil.getFormattedDate(returns.getCreatedOn()))
            .serialNo(returns.getSerialNo())
            .customer(returns.getCustomer().getCustomerName())
            .branch(returns.getBranch().getBranchName())
            .build();
  }
}
