package com.flagship.repository;

import com.flagship.model.db.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
  Optional<Customer> findByCustomerId(String customerId);
}
