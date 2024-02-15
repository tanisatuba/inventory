package com.flagship.repository;

import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportDetailsRepository extends PagingAndSortingRepository<ImportDetails, Long> {

  List<ImportDetails> findByProduct(Product products);

  Optional<ImportDetails> findByProductAndImportMaster(Product product, ImportMaster importMaster);

  List<ImportDetails> findByImportMaster(ImportMaster importMaster);

  List<ImportDetails> findByImportMasterAndProduct(ImportMaster importMaster, Product products);

  Optional<ImportDetails> findByProductAndImportMasterAndWarehouse(Product products, ImportMaster importMaster,
                                                                  Warehouse moveFrom);
}
