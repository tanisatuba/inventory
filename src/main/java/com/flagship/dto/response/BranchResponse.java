package com.flagship.dto.response;

import com.flagship.model.db.Branch;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class BranchResponse {
  private Integer code;
  private String message;
  private String supplier;
  private String branchCode;
  private String branchName;
  private String branchAddress;

  public static BranchResponse from(String message, Branch branch) {
    return BranchResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .supplier(branch.getSupplier().getSupplierName())
            .branchName(branch.getBranchName())
            .branchAddress(branch.getAddress())
            .build();
  }
}
