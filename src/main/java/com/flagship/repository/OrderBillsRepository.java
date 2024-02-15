package com.flagship.repository;

import com.flagship.model.db.OrderBills;
import com.flagship.model.db.OrderMaster;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderBillsRepository extends PagingAndSortingRepository<OrderBills, Long> {
  List<OrderBills> findByOrder(OrderMaster orderMaster);

  Optional<OrderBills> findFirstByOrderOrderByCreatedOnDesc(OrderMaster orderMaster);
}
