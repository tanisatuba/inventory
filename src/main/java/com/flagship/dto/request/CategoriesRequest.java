package com.flagship.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class CategoriesRequest {
  @Valid
  @NotEmpty
  private String categoryId;
  @Valid
  @NotEmpty
  private String categoryName;
  @Valid
  @NotEmpty
  private String user;
}
