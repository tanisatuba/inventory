package com.flagship.dto.response;

import com.flagship.constant.enums.CustomerType;
import com.flagship.model.db.Customer;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class CustomerResponse {
  private Integer code;
  private String message;
  private String customerId;
  private String customerName;
  private String company;
  private CustomerType customerType;
  private String address;
  private Integer creditTerm;
  private String phoneNumber;
  private String binNo;
  private String supplier;

  public static CustomerResponse from(String message, Customer customer) {
    return CustomerResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .customerId(customer.getCustomerId())
            .customerName(customer.getCustomerName())
            .company(customer.getCompany())
            .customerType(customer.getCustomerType())
            .address(customer.getAddress())
            .creditTerm(customer.getCreditTerm() != null ? customer.getCreditTerm() : 0)
            .phoneNumber(customer.getPhoneNumber())
            .binNo(customer.getBinNo())
            .supplier(customer.getSupplier() != null ? customer.getSupplier().getSupplierName() : null)
            .build();
  }
}
