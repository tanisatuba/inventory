package com.flagship.repository;

import com.flagship.model.db.Requisition;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequisitionRepository extends PagingAndSortingRepository<Requisition, Long> {
  Optional<Requisition> findFirstByOrderBySerialNoDesc();

  List<Requisition> findBySerialNo(Long serial);
}
