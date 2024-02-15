package com.flagship.dto.response;

import com.flagship.model.db.OrderMaster;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class AllLedgerResponse {
  private String salesPerson;
  private String branch;
  private String orderDate;
  private Long orderId;
  private String haveToPay;
  private Double discount;
  private List<SingleLedgerResponse> singleLedgerResponseList;
  public static AllLedgerResponse from(OrderMaster orderMaster, List<SingleLedgerResponse> singleLedgerResponseList,
                                       Double discount){
    return AllLedgerResponse.builder()
            .salesPerson(orderMaster.getOrderBy().getSalesPersonName())
            .branch(orderMaster.getBranch().getBranchName())
            .orderDate(DateUtil.getFormattedDate(orderMaster.getOrderDate()))
            .orderId(orderMaster.getOrderId())
            .haveToPay(DateUtil.getFormattedDate(orderMaster.getOrderDate().plusDays(orderMaster.getCreditTerm())))
            .discount(discount)
            .singleLedgerResponseList(singleLedgerResponseList)
            .build();
  }
}
