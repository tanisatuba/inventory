package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AllProductAndArticleAndSaleResponse {
  private Integer serialNo;
  private String productName;
  private String productId;
  private List<CompanyResponse> companies;
  public static AllProductAndArticleAndSaleResponse from(Integer serialNo, String productId, String productName, List<CompanyResponse> companies){
    return AllProductAndArticleAndSaleResponse.builder()
            .serialNo(serialNo)
            .productId(productId)
            .productName(productName)
            .companies(companies)
            .build();
  }
}
