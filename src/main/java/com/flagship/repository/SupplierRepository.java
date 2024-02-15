package com.flagship.repository;

import com.flagship.model.db.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long> {
  Optional<Supplier> findBySupplierId(String supplierId);
}
