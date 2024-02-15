package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import com.flagship.model.db.OrderDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleBillsDetailsResponse {
  private String product;
  private UOM uom;
  private String article;
  private String sale;
  private Double quantity;
  private Double discount;
  private Double price;
  private Double total;

  public static SingleBillsDetailsResponse from(OrderDetails orderDetails) {
    SingleBillsDetailsResponse.SingleBillsDetailsResponseBuilder builder = SingleBillsDetailsResponse.builder()
            .product(orderDetails.getProduct().getProductName())
            .uom(orderDetails.getUom())
            .quantity(orderDetails.getQuantity())
            .discount(orderDetails.getDiscount())
            .price(orderDetails.getPrice())
            .total(orderDetails.getTotalPrice());
    if (orderDetails.getSale() != null) {
      builder.sale(orderDetails.getSale().getSaleCode()).article(orderDetails.getSale().getArticle());
    } else {
      builder.sale(null).article(null);
    }
    return builder.build();
  }
}
