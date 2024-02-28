package com.flagship.dto.request;

import com.flagship.constant.enums.ChallanCompany;
import com.flagship.constant.enums.CustomerType;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderMasterRequest {
  @Valid
  @NotNull
  private CommonRequest customer;
  private String companyName;
  private String address;
  @Valid
  @NotEmpty
  private String deliveryAddress;
  private String supplierId;
  private CommonRequest branch;
  @Valid
  @NotNull
  private CustomerType customerType;
  @Valid
  @NotEmpty
  private String orderDate;
  @Valid
  @NotEmpty
  private String deliveryDate;
  private Integer creditTerm;
  private ChallanCompany challanCompany;
  private Integer challanNo;
  private String binNo;
  @Valid
  @NotNull
  private CommonRequest orderBy;
  @Valid
  @NotEmpty
  private String user;
  @Valid
  @NotNull
  private List<OrderDetailsRequest> orderDetails;
}
