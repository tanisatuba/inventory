package com.flagship.repository;

import com.flagship.constant.enums.OrderStatus;
import com.flagship.constant.enums.Status;
import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.ImportMaster;
import com.flagship.model.db.OrderDetails;
import com.flagship.model.db.OrderMaster;
import com.flagship.model.db.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends PagingAndSortingRepository<OrderDetails, Long> {
  List<OrderDetails> findAllByOrder(OrderMaster orderId);

  List<OrderDetails> findByProduct(Product product);

  List<OrderDetails> findByOrderAndStatus(OrderMaster orderMaster, OrderStatus orderStatus);

  Optional<OrderDetails> findByOrderAndProductAndShipment(OrderMaster order, Product product, ImportMaster importMaster);

  Optional<OrderDetails> findByOrderAndProduct(OrderMaster orderMaster, Product product);

  Optional<OrderDetails> findByOrderAndProductAndWarehouse(OrderMaster orderId, Product product, Warehouse warehouse);

  List<OrderDetails> findByProductAndOrder(Product product, OrderMaster order);

  List<OrderDetails> findAllByOrderAndOrderStatus(OrderMaster orderMaster, Status status);
}
