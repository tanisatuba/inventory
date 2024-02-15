package com.flagship.repository;

import com.flagship.model.db.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
  Optional<Brand> findByBrandId(String brandId);
}
