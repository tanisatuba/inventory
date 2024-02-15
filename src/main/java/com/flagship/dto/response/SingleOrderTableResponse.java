package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import com.flagship.model.db.OrderDetails;
import com.flagship.model.db.OrderMaster;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleOrderTableResponse {
  private String orderDate;
  private String orderBy;
  private String deliveryDate;
  private Long invoice;
  private String customer;
  private String branch;
  private String item;
  private UOM uom;
  private Double quantity;
  private Double unitPrice;
  private Double totalBill;
  private Double grandTotal;
  private Double itemDiscount;

  public static SingleOrderTableResponse from(OrderMaster orderMaster, OrderDetails orderDetails) {
    SingleOrderTableResponse.SingleOrderTableResponseBuilder builder = SingleOrderTableResponse.builder()
            .orderDate(DateUtil.getFormattedDate(orderMaster.getOrderDate()))
            .orderBy(orderMaster.getOrderBy().getSalesPersonName())
            .deliveryDate(DateUtil.getFormattedDate(orderMaster.getDeliveryDate()))
            .invoice(orderMaster.getOrderId())
            .customer(orderMaster.getCustomer().getCustomerName())
            .branch(orderMaster.getBranch() != null ? orderMaster.getBranch().getBranchName() : null)
            .item(orderDetails.getProduct().getProductName())
            .uom(orderDetails.getUom())
            .quantity(orderDetails.getQuantity())
            .unitPrice(orderDetails.getPrice())
            .totalBill(orderDetails.getTotalPrice())
            .grandTotal(null)
            .itemDiscount(orderDetails.getDiscount() != null ? orderDetails.getDiscount() : null);
    return builder.build();
  }
}
