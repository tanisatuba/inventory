package com.flagship.dto.request;

import com.flagship.constant.enums.UOM;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class OrderDetailsRequest {
  @Valid
  @NotNull
  private CommonRequest product;
  @Valid
  @NotNull
  private CommonRequest shipment;
  @Valid
  @NotNull
  private CommonRequest warehouse;
  private String saleCode;
  private String article;
  private Double vat;
  @Valid
  @NotNull
  private UOM uom;
  @Valid
  @NotNull
  private Double quantity;
  @Valid
  @NotNull
  private Double available;
  private Double discount;
  private String remarks;
  @Valid
  @NotNull
  private Double price;
  private Double totalPrice;
}
