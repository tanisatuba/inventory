package com.flagship.dto.response;

import com.flagship.model.db.OrderMaster;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

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
  private Double totalSales;
  private Double totalPayments;
  private List<SingleLedgerResponse> singleLedgerResponseList;

  public static AllLedgerResponse from(OrderMaster orderMaster, List<SingleLedgerResponse> singleLedgerResponseList,
                                       Double discount, Double totalSales, Double totalPayments) {
    return AllLedgerResponse.builder()
            .salesPerson(orderMaster.getOrderBy().getSalesPersonName())
            .branch(orderMaster.getBranch().getBranchName())
            .orderDate(DateUtil.getFormattedDate(orderMaster.getOrderDate()))
            .orderId(orderMaster.getOrderId())
            .haveToPay(DateUtil.getFormattedDate(orderMaster.getOrderDate().plusDays(orderMaster.getCreditTerm())))
            .discount(discount)
            .totalSales(totalSales)
            .totalPayments(totalPayments)
            .singleLedgerResponseList(singleLedgerResponseList)
            .build();
  }
}
