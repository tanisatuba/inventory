package com.flagship.dto.response;

import com.flagship.model.db.Categories;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class CategoriesResponse {
  private Integer code;
  private String message;
  private String categoryId;
  private String categoryName;

  public static CategoriesResponse from(String message, Categories categories) {
    return CategoriesResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .categoryId(categories.getCategoryId())
            .categoryName(categories.getCategoryName())
            .build();
  }
}
