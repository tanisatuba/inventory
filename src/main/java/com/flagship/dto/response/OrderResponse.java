package com.flagship.dto.response;

import com.flagship.constant.enums.CustomerType;
import com.flagship.model.db.OrderMaster;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
  private Integer code;
  private String message;
  private Long orderId;
  private String customerName;
  private String company;
  private String supplier;
  private String branch;
  private CustomerType customerType;
  private ZonedDateTime orderDate;
  private ZonedDateTime deliveryDate;
  private Integer creditTerm;
  private Integer challanNo;
  private String orderBy;
  private List<OrderDetailsResponse> orderDetailsResponses;

  public static OrderResponse from(String message, OrderMaster orderMaster, List<OrderDetailsResponse> orderDetailsResponses) {
    return OrderResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .orderId(orderMaster.getOrderId())
            .challanNo(orderMaster.getChallan())
            .customerName(orderMaster.getCustomer().getCustomerName())
            .company(orderMaster.getCompanyName())
            .customerType(orderMaster.getCustomerType())
            .orderDate(orderMaster.getOrderDate())
            .deliveryDate(orderMaster.getDeliveryDate())
            .creditTerm(orderMaster.getCreditTerm())
            .orderBy(orderMaster.getCreatedBy().getName())
            .orderDetailsResponses(orderDetailsResponses)
            .build();
  }
}
