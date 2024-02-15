package com.flagship.dto.response;

import com.flagship.model.db.OrderDetails;
import com.flagship.model.db.OrderMaster;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyOrderProductsResponse {
  private String customer;
  private String product;
  private Double quantity;

  public static DailyOrderProductsResponse from(OrderDetails orderDetails, OrderMaster orderMaster) {
    return DailyOrderProductsResponse.builder()
            .customer(orderMaster.getCustomer().getCustomerName())
            .product(orderDetails.getProduct().getProductName())
            .quantity(orderDetails.getQuantity())
            .build();
  }
}
