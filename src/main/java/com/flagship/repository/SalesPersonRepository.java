package com.flagship.repository;

import com.flagship.model.db.SalesPerson;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesPersonRepository extends PagingAndSortingRepository<SalesPerson, Long> {
  Optional<SalesPerson> findBySalesPersonId(String salesPersonId);
}
