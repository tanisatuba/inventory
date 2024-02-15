package com.flagship.repository;

import com.flagship.model.db.Branch;
import com.flagship.model.db.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends PagingAndSortingRepository<Branch, Long> {

  Optional<Branch> findByBranchNameAndSupplier(String branch, Supplier supplier);

  List<Branch> findBySupplier(Supplier supplier);
}
