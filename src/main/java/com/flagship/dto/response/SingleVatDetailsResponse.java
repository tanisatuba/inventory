package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import com.flagship.model.db.OrderDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleVatDetailsResponse {
  private String product;
  private UOM uom;
  private Double quantity;
  private Double price;
  private Double total;
  private Double tax;
  private Double taxAmount;
  private String vat;
  private Double vatAmount;
  private Double totalAmountWithVatTax;

  public static SingleVatDetailsResponse from(OrderDetails orderDetails) {
    SingleVatDetailsResponse.SingleVatDetailsResponseBuilder builder = SingleVatDetailsResponse.builder()
            .product(orderDetails.getProduct().getProductName())
            .uom(orderDetails.getUom())
            .quantity(orderDetails.getQuantity());
    if (orderDetails.getVat() != null) {
      builder.vat(orderDetails.getVat() + "%");

      // Calculate price, total, and totalVatAmount with rounding
      Double productPrice = Math.round((orderDetails.getPrice() * 100.0) / (orderDetails.getVat() + 100.0) * 100.0) / 100.0;
      Double totalProductPrice = Math.round(productPrice * orderDetails.getQuantity() * 100.0) / 100.0;
      Double vatPrice = Math.round((orderDetails.getPrice() - Math.round((orderDetails.getPrice() * 100.0) /
              (orderDetails.getVat() + 100.0) * 100.0) / 100.0) *100) / 100.0;
      Double totalVatAmount = Math.round(vatPrice * orderDetails.getQuantity() * 100.0) / 100.0;

      builder.vatAmount(totalVatAmount);
      builder.totalAmountWithVatTax(Math.round((totalProductPrice + totalVatAmount) * 100.0) / 100.0);
      builder.price(productPrice);
      builder.total(totalProductPrice);
    } else {
      builder.vat("0.00%");
      builder.vatAmount(null);
      builder.totalAmountWithVatTax(orderDetails.getTotalPrice());
      builder.price(orderDetails.getPrice());
      builder.total(orderDetails.getTotalPrice());
    }
    return builder.build();
  }
}
