package com.flagship.repository;

import com.flagship.constant.enums.ChallanCompany;
import com.flagship.model.db.Customer;
import com.flagship.model.db.OrderMaster;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderMasterRepository extends PagingAndSortingRepository<OrderMaster, Long> {
  List<OrderMaster> findAllByCreatedOnBetween(ZonedDateTime start, ZonedDateTime end);


  Optional<OrderMaster> findFirstByOrderByOrderIdDesc();

  Optional<OrderMaster> findByOrderId(Long orderId);

  List<OrderMaster> findByCustomerOrderByCreatedOnDesc(Customer customer);

  List<OrderMaster> findByOrderDateBetween(ZonedDateTime start, ZonedDateTime end);

  List<OrderMaster> findByDeliveryDateBetween(ZonedDateTime start, ZonedDateTime end);

  List<OrderMaster> findByCustomer(Customer customer);

  List<OrderMaster> findByOrderByCreatedOnDesc();

  Optional<OrderMaster> findFirstByChallanCompanyOrderByOrderIdDesc(ChallanCompany challanCompany);
}
