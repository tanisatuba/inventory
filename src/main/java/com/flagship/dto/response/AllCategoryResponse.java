package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllCategoryResponse {
  private Integer code;
  private List<SingleCategoryResponse> categoryResponseList;

  public static AllCategoryResponse from(List<SingleCategoryResponse> singleCategoryResponses) {
    return AllCategoryResponse.builder()
            .code(HttpStatus.SC_OK)
            .categoryResponseList(singleCategoryResponses)
            .build();
  }
}
