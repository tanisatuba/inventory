package com.flagship.dto.response;

import com.flagship.model.db.OrderMaster;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleBillResponse {
  private Long orderId;
  private String customerId;
  private String customer;
  private String supplier;
  private String orderDate;
  private String deliveryDate;
  private String company;
  private String address;
  private String delivery;
  private String bin;
  private Integer challan;
  private Double sales;
  private Double due;
  private String branch;
  private String billTo;
  private String salesPerson;

  public static SingleBillResponse from(OrderMaster orderMaster, Double sales, Double due) {
    SingleBillResponse.SingleBillResponseBuilder builder = SingleBillResponse.builder()
            .orderId(orderMaster.getOrderId())
            .orderDate(DateUtil.getFormattedDate(orderMaster.getOrderDate()))
            .deliveryDate(DateUtil.getFormattedDate(orderMaster.getDeliveryDate()))
            .company(orderMaster.getCompanyName())
            .delivery(orderMaster.getBranch().getBranchName() +"/" + orderMaster.getAddress())
            .challan(orderMaster.getChallan())
            .sales(sales)
            .due(due)
            .salesPerson(orderMaster.getOrderBy().getSalesPersonName());
    if (orderMaster.getSupplier() != null) {
      builder.supplier(orderMaster.getSupplier().getSupplierId()); 
    } else {
      builder.supplier(null);
    }
    if (orderMaster.getBranch() != null && orderMaster.getCustomer() != null) {
      builder.branch(orderMaster.getBranch().getBranchName());
      builder.billTo(orderMaster.getCustomer().getCompany() + "/" + orderMaster.getBranch().getBranchName());
    } else {
      builder.branch(null);
      builder.billTo(null);
    }
    if(orderMaster.getCustomer() != null){
      builder.customerId(orderMaster.getCustomer().getCustomerId());
      builder.customer(orderMaster.getCustomer().getCustomerName());
      builder.address(orderMaster.getCustomer().getAddress());
      builder.bin(orderMaster.getCustomer().getBinNo());
    }else {
      builder.customerId(null);
      builder.customer(null);
      builder.address(null);
      builder.bin(null);
    }
    return builder.build();
  }
}
