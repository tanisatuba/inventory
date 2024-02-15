package com.flagship.dto.response;

import com.flagship.model.db.SalesPerson;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleSalesPersonResponse {
  private String salesPersonId;
  private String salesPersonName;
  private String phoneNumber;
  private String area;

  public static SingleSalesPersonResponse from(SalesPerson salesPerson) {
    return SingleSalesPersonResponse.builder()
            .salesPersonId(salesPerson.getSalesPersonId())
            .salesPersonName(salesPerson.getSalesPersonName())
            .phoneNumber(salesPerson.getPhoneNumber())
            .area(salesPerson.getArea())
            .build();
  }
}
