package com.flagship.dto.response;

import com.flagship.model.db.Brand;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleBrandResponse {
  private String brandId;
  private String brandName;

  public static SingleBrandResponse from(Brand brand) {
    return SingleBrandResponse.builder()
            .brandId(brand.getBrandId())
            .brandName(brand.getBrandName())
            .build();
  }
}
