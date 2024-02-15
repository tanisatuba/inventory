package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllBranchResponse {
  private Integer code;
  private List<SingleBranchResponse> branchList;

  public static AllBranchResponse from(List<SingleBranchResponse> branchList) {
    return AllBranchResponse.builder()
            .code(HttpStatus.SC_OK)
            .branchList(branchList)
            .build();
  }
}
