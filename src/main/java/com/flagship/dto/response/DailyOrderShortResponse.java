package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class DailyOrderShortResponse {
  private Integer code;
  private List<DailyShortResponse> dailyShortResponses;

  public static DailyOrderShortResponse from(List<DailyShortResponse> dailyShortResponses) {
    return DailyOrderShortResponse.builder()
            .code(HttpStatus.SC_OK)
            .dailyShortResponses(dailyShortResponses)
            .build();
  }
}
