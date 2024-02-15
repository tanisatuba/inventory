package com.flagship.dto.response;

import com.flagship.model.db.ImportDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleWarehouse {
  private String warehouse;

  public static SingleWarehouse from(ImportDetails details) {
    return SingleWarehouse.builder()
            .warehouse(details.getWarehouse().getName())
            .build();
  }
}
