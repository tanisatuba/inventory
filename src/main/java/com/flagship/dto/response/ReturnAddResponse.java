package com.flagship.dto.response;

import com.flagship.constant.enums.Cause;
import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.Returns;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ReturnAddResponse {
  private Integer code;
  private String message;
  private Warehouse warehouse;
  private String deliveryMan;
  private String customer;
  private String branch;
  private List<ReturnResponse> detailsResponseList;

  public static ReturnAddResponse from(String message, Warehouse warehouse, String name,
                                    String customerName, String branch, List<ReturnResponse> detailsResponseList) {
    return ReturnAddResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .warehouse(warehouse)
            .deliveryMan(name)
            .customer(customerName)
            .branch(branch)
            .detailsResponseList(detailsResponseList)
            .build();
  }
}
