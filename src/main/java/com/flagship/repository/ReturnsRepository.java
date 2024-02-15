package com.flagship.repository;

import com.flagship.model.db.OrderMaster;
import com.flagship.model.db.Returns;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReturnsRepository extends PagingAndSortingRepository<Returns, Long> {
  List<Returns> findByOrder(OrderMaster order);

  Optional<Returns> findFirstByOrderBySerialNoDesc();

  List<Returns> findBySerialNo(Long serial);
}
