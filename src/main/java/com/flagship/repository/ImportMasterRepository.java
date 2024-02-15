package com.flagship.repository;

import com.flagship.model.db.ImportMaster;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImportMasterRepository extends PagingAndSortingRepository<ImportMaster, Long> {
  Optional<ImportMaster> findByShipmentNo(String shipmentNo);
}
