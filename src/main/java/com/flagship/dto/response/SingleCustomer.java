package com.flagship.dto.response;

import com.flagship.constant.enums.CustomerType;
import com.flagship.model.db.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleCustomer {
  private String customerId;
  private String customerName;
  private String company;
  private CustomerType customerType;
  private String address;
  private Integer creditTerm;
  private String phoneNumber;
  private String binNo;
  private String supplier;
  private String supplierId;

  public static SingleCustomer from(Customer customer) {
    return SingleCustomer.builder()
            .customerId(customer.getCustomerId())
            .customerName(customer.getCustomerName())
            .company(customer.getCompany())
            .customerType(customer.getCustomerType())
            .address(customer.getAddress())
            .creditTerm(customer.getCreditTerm() != null ? customer.getCreditTerm() : 0)
            .phoneNumber(customer.getPhoneNumber())
            .binNo(customer.getBinNo())
            .supplier(customer.getSupplier() != null ? customer.getSupplier().getSupplierName() : null)
            .supplierId(customer.getSupplier() != null ? customer.getSupplier().getSupplierId() : null)
            .build();
  }

}
