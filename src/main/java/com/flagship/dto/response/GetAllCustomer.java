package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class GetAllCustomer {
  private Integer code;
  private List<SingleCustomer> customerList;

  public static GetAllCustomer from(List<SingleCustomer> customerList) {
    return GetAllCustomer.builder()
            .code(HttpStatus.SC_OK)
            .customerList(customerList)
            .build();
  }
}
