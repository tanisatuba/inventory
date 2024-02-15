package com.flagship.dto.response;

import com.flagship.model.db.Categories;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleCategoryResponse {
  private String categoryId;
  private String categoryName;

  public static SingleCategoryResponse from(Categories categories) {
    return SingleCategoryResponse.builder()
            .categoryId(categories.getCategoryId())
            .categoryName(categories.getCategoryName())
            .build();
  }
}
