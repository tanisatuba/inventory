package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.OrderDetails;
import com.flagship.model.db.OrderMaster;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PendingOrderResponse {
  private Long orderId;
  private String shipment;
  private String customer;
  private String company;
  private String product;
  private String productId;
  private UOM uom;
  private Double quantity;
  private Double unitPrice;
  private Double totalBill;
  private Warehouse warehouse;

  public static PendingOrderResponse from(OrderMaster orderMaster, OrderDetails orderDetails) {
    return PendingOrderResponse.builder()
            .orderId(orderDetails.getOrder().getOrderId())
            .shipment(orderDetails.getShipment().getShipmentNo())
            .customer(orderMaster.getCustomer().getCustomerName())
            .company(orderMaster.getCompanyName() != null ? orderMaster.getCompanyName() : null)
            .productId(orderDetails.getProduct().getProductId())
            .product(orderDetails.getProduct().getProductName())
            .uom(orderDetails.getUom())
            .quantity(orderDetails.getQuantity())
            .unitPrice(orderDetails.getPrice())
            .totalBill(orderDetails.getTotalPrice())
            .warehouse(orderDetails.getWarehouse())
            .build();
  }
}
