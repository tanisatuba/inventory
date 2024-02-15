package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class BillsDetailsResponse {
  private Integer code;
  private SingleBillResponse singleBillResponse;
  private List<SingleBillsDetailsResponse> singleBillsDetailsResponses;

  public static BillsDetailsResponse from(SingleBillResponse singleBillResponse,
                                          List<SingleBillsDetailsResponse> singleBillsDetailsResponses) {
    return BillsDetailsResponse.builder()
            .code(HttpStatus.SC_OK)
            .singleBillResponse(singleBillResponse)
            .singleBillsDetailsResponses(singleBillsDetailsResponses)
            .build();
  }
}
