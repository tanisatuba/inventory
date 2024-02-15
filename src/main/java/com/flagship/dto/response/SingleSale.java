package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class SingleSale {
  private Integer code;
  private String saleCode;
  private String article;

  public static SingleSale from(String saleCode, String article) {
    return SingleSale.builder()
            .code(HttpStatus.SC_OK)
            .article(article)
            .saleCode(saleCode)
            .build();
  }
}
