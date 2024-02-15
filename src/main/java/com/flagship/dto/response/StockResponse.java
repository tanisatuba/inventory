package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import com.flagship.model.db.Stock;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockResponse {
  private String productName;
  private UOM uom;
  private Double totalBuy;
  private Double totalSell;
  private Double inStock;

  public static StockResponse from(Stock stock) {
    return StockResponse.builder()
            .productName(stock.getProduct().getProductName())
            .uom(stock.getUom())
            .totalBuy(stock.getTotalBuy())
            .totalSell(stock.getTotalSell())
            .inStock(stock.getInStock())
            .build();
  }
}
