package com.flagship.dto.response;

import com.flagship.constant.enums.Warehouse;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class AllWastageResponse {
  private Integer code;
  private Long serialNo;
  private String name;
  private String warehouse;
  private String createdOn;
  private List<SingleWastageResponse> wastageResponse;
  public static AllWastageResponse from(Warehouse warehouse, String name, ZonedDateTime createdOn, Long serialNo, List<SingleWastageResponse> wastageResponse){
    return AllWastageResponse.builder()
            .code(HttpStatus.SC_OK)
            .name(name)
            .warehouse(warehouse.getName())
            .createdOn(DateUtil.getFormattedDate(createdOn))
            .serialNo(serialNo)
            .wastageResponse(wastageResponse)
            .build();
  }
}
