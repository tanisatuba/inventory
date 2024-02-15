package com.flagship.dto.response;

import com.flagship.model.db.Branch;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleBranchResponse {
  private String branchCode;
  private String branchName;
  private String address;

  public static SingleBranchResponse from(Branch branch) {
    return SingleBranchResponse.builder()
            .branchName(branch.getBranchName())
            .address(branch.getAddress())
            .build();
  }
}
