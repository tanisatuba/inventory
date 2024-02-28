package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.ImportDetails;
import com.flagship.model.db.ImportMaster;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleImportResponse {
  private String shipmentNo;
  private String shipmentCountry;
  private String shipmentDate;
  private String productId;
  private String product;
  private String category;
  private String brand;
  private String production;
  private String country;
  private Warehouse warehouse;
  private String expire;
  private Double cartoon;
  private Double piece;
  private Double kgLt;
  private UOM uom;
  private Double price;
  private Double total;

  public static SingleImportResponse from(ImportMaster importMaster, ImportDetails importDetails) {
    return SingleImportResponse.builder()
            .shipmentNo(importMaster.getShipmentNo())
            .shipmentCountry(importMaster.getCountry().getCountryName())
            .shipmentDate(DateUtil.getFormattedDate(importMaster.getDate()))
            .productId(importDetails.getProduct().getProductId())
            .product(importDetails.getProduct().getProductName())
            .category(importDetails.getCategories().getCategoryName())
            .brand(importDetails.getBrand().getBrandName())
            .production(DateUtil.getFormattedDate(importDetails.getProduction()))
            .country(importDetails.getCountry().getCountryName())
            .warehouse(importDetails.getWarehouse())
            .expire(DateUtil.getFormattedDate(importDetails.getExpire()))
            .cartoon(importDetails.getCartoon())
            .piece(importDetails.getPiece())
            .kgLt(importDetails.getKgLt())
            .uom(importDetails.getUom())
            .price(importDetails.getPrice())
            .total(importDetails.getTotal())
            .build();
  }
}
