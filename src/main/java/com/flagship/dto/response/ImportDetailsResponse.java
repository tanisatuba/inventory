package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.ImportDetails;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class ImportDetailsResponse {
  private String product;
  private String category;
  private String brand;
  private ZonedDateTime production;
  private String country;
  private Warehouse warehouse;
  private ZonedDateTime expire;
  private Double cartoon;
  private Double piece;
  private Double kgLt;
  private UOM uom;
  private Double price;
  private Double total;
  private String receiverName;

  public static ImportDetailsResponse from(ImportDetails importDetails) {
    return ImportDetailsResponse.builder()
            .product(importDetails.getProduct().getProductName())
            .category(importDetails.getCategories().getCategoryName())
            .brand(importDetails.getBrand().getBrandName())
            .production(importDetails.getProduction())
            .country(importDetails.getCountry().getCountryName())
            .warehouse(importDetails.getWarehouse())
            .expire(importDetails.getExpire())
            .cartoon(importDetails.getCartoon())
            .piece(importDetails.getPiece())
            .kgLt(importDetails.getKgLt())
            .uom(importDetails.getUom())
            .price(importDetails.getPrice())
            .total(importDetails.getTotal())
            .receiverName(importDetails.getCreatedBy().getName())
            .build();

  }
}
