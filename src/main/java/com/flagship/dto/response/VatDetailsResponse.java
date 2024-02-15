package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class VatDetailsResponse {
  private Integer code;
  private SingleBillResponse singleBillResponse;
  private List<SingleVatDetailsResponse> singleVatDetailsResponses;

  public static VatDetailsResponse from(SingleBillResponse singleBillResponse,
                                        List<SingleVatDetailsResponse> singleVatDetailsResponses) {
    return VatDetailsResponse.builder()
            .code(HttpStatus.SC_OK)
            .singleBillResponse(singleBillResponse)
            .singleVatDetailsResponses(singleVatDetailsResponses)
            .build();
  }
}
