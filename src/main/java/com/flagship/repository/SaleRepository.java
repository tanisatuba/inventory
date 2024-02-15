package com.flagship.repository;

import com.flagship.model.db.Product;
import com.flagship.model.db.Sale;
import com.flagship.model.db.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {
  Optional<Sale> findByProductAndSupplier(Product product, Supplier supplier);

  Optional<Sale> findBySupplierAndProductAndArticleAndSaleCode(Supplier supplier, Product product, String article, String saleCode);

  Optional<Sale> findBySaleCode(String sale);

  Optional<Sale> findBySupplierAndProductAndArticle(Supplier supplier, Product product, String article);

  Optional<Sale> findBySupplierAndProductAndSaleCode(Supplier supplier, Product product, String saleCode);

  Optional<Sale> findBySupplierAndProduct(Supplier supplier, Product product);

  List<Sale> findByProduct(Product product);
}
