package com.flagship.repository;

import com.flagship.model.db.Product;
import com.flagship.model.db.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, Long> {
  Optional<Stock> findByProduct(Product product);
}
