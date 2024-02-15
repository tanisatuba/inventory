package com.flagship.dto.response;

import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class ReturnDetailsResponse {
  private Integer code;
  private String name;
  private String date;
  private Double piece;
  private String cartoonKgLt;
  private List<SingleReturnResponse> returnResponses;

  public static ReturnDetailsResponse from(String name, ZonedDateTime dateTime, Double piece, Double cartoon, Double kgLt,
                                           List<SingleReturnResponse> returnResponses) {
    return ReturnDetailsResponse.builder()
            .code(HttpStatus.SC_OK)
            .name(name)
            .date(DateUtil.getFormattedDate(dateTime))
            .piece(piece)
            .cartoonKgLt(cartoon + " ctn: " + kgLt + " kg")
            .returnResponses(returnResponses)
            .build();
  }
}
