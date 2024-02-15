package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class FinalResponse {
  private Integer code;
  private List<AllProductAndArticleAndSaleResponse> responseList;
  public static FinalResponse from(List<AllProductAndArticleAndSaleResponse> responseList){
    return FinalResponse.builder()
            .code(HttpStatus.SC_OK)
            .responseList(responseList)
            .build();
  }
}
