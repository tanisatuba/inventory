package com.flagship.dto.response;

import com.flagship.model.db.SalesPerson;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class SalesPersonResponse {
  private Integer code;
  private String message;
  private String salesPersonId;
  private String salesPersonName;
  private String phoneNumber;
  private String area;

  public static SalesPersonResponse from(String message, SalesPerson salesPerson) {
    return SalesPersonResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .salesPersonId(salesPerson.getSalesPersonId())
            .salesPersonName(salesPerson.getSalesPersonName())
            .phoneNumber(salesPerson.getPhoneNumber())
            .area(salesPerson.getArea())
            .build();
  }
}