package com.flagship.dto.response;

import com.flagship.model.db.Brand;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class BrandResponse {
  private Integer code;
  private String message;
  private String brandId;
  private String brandName;

  public static BrandResponse from(String message, Brand brand) {
    return BrandResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .brandId(brand.getBrandId())
            .brandName(brand.getBrandName())
            .build();
  }
}
