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
public class WastageDetailsResponse {
  private Integer code;
  private Long serialNo;
  private String name;
  private String warehouse;
  private String createdOn;
  private Double piece;
  private String cartoonKgLt;
  private List<SingleWastageSlipResponse> wastageResponses;

  public static WastageDetailsResponse from(Double piece, Double cartoon, Double kgLt,
                                            Warehouse warehouse, String name, ZonedDateTime createdOn, Long serialNo,
                                            List<SingleWastageSlipResponse> wastageResponses) {
    return WastageDetailsResponse.builder()
            .code(HttpStatus.SC_OK)
            .piece(piece)
            .cartoonKgLt(cartoon + " ctn: " + kgLt + " kg")
            .name(name)
            .warehouse(warehouse.getName())
            .createdOn(DateUtil.getFormattedDate(createdOn))
            .serialNo(serialNo)
            .wastageResponses(wastageResponses)
            .build();
  }
}
