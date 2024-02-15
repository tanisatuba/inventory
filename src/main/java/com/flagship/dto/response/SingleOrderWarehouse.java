package com.flagship.dto.response;

import com.flagship.model.db.ImportDetails;
import com.flagship.model.db.OrderDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleOrderWarehouse {
  private String warehouse;

  public static SingleOrderWarehouse from(OrderDetails details) {
    return SingleOrderWarehouse.builder()
            .warehouse(details.getWarehouse().getName())
            .build();
  }
}
