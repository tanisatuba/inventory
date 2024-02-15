package com.flagship.repository;

import com.flagship.model.db.Categories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends PagingAndSortingRepository<Categories, Long> {
  Optional<Categories> findByCategoryId(String categoryId);
}
