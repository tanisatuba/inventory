package com.flagship.dto.response;

import com.flagship.model.db.OrderMaster;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class ChallanResponse {
  private Integer code;
  private Integer challan;

  public static ChallanResponse from(OrderMaster orderMaster) {
    return ChallanResponse.builder()
            .code(HttpStatus.SC_OK)
            .challan(orderMaster.getChallan())
            .build();
  }
}
