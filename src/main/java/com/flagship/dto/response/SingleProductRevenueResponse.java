package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleProductRevenueResponse {
  private String product;
  private Double totalBuy;
  private Double totalSell;
  private Double inStock;
  private Double averageBuyingPrice;
  private Double averageSellingPrice;
  private Double totalBuyingPrice;
  private Double totalSellingPrice;
  private Double revenue;

  public static SingleProductRevenueResponse from(String product, Double totalBuy, Double totalSell, Double inStock,
                                                  Double averageBuyingPrice, Double averageSellingPrice,
                                                  Double totalBuyingPrice, Double totalSellingPrice, Double revenue) {
    return SingleProductRevenueResponse.builder()
            .product(product)
            .totalBuy(totalBuy)
            .totalSell(totalSell)
            .inStock(inStock)
            .averageBuyingPrice(averageBuyingPrice)
            .averageSellingPrice(averageSellingPrice)
            .totalBuyingPrice(totalBuyingPrice)
            .totalSellingPrice(totalSellingPrice)
            .revenue(revenue)
            .build();
  }
}
