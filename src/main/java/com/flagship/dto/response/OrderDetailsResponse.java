package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import com.flagship.model.db.OrderDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailsResponse {
  private Long orderId;
  private String productName;
  private String article;
  private String saleName;
  private Double vat;
  private UOM uom;
  private Double quantity;
  private Double discount;
  private String remarks;
  private Double price;
  private Double total;

  public static OrderDetailsResponse from(OrderDetails orderDetails) {
    OrderDetailsResponse.OrderDetailsResponseBuilder builder = OrderDetailsResponse.builder()
            .orderId(orderDetails.getOrder().getOrderId())
            .productName(orderDetails.getProduct().getProductName())
            .vat(orderDetails.getVat())
            .uom(orderDetails.getUom())
            .quantity(orderDetails.getQuantity())
            .discount(orderDetails.getDiscount())
            .remarks(orderDetails.getRemarks())
            .price(orderDetails.getPrice())
            .total(orderDetails.getTotalPrice());
    if (orderDetails.getSale() != null) {
      builder.article(orderDetails.getSale().getArticle());
      builder.saleName(orderDetails.getSale().getSaleCode());
    }
    return builder.build();
  }
}
