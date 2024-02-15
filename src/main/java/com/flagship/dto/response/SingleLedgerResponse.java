package com.flagship.dto.response;

import com.flagship.model.db.OrderBills;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleLedgerResponse {
  private String paymentDate;
  private Double payments;
  private Double due;
  private Double sales;
  public static SingleLedgerResponse from(OrderBills orderBills){
    return SingleLedgerResponse.builder()
            .paymentDate(DateUtil.getFormattedDate(orderBills.getCreatedOn()))
            .payments(orderBills.getPayment())
            .due(orderBills.getDue())
            .sales(orderBills.getSales())
            .build();
  }
}
