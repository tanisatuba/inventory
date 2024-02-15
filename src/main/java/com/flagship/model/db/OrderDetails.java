package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.*;
import com.flagship.constant.enums.OrderStatus;
import com.flagship.constant.enums.UOM;
import com.flagship.constant.enums.Warehouse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbOrderDetails.TABLE_NAME)
@ToString
public class OrderDetails implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbOrderDetails.ID)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderDetails.ORDER, referencedColumnName = DbOrderMaster.ORDER_ID, nullable = false)
  private OrderMaster order;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderDetails.PRODUCT, referencedColumnName = DbProduct.PRODUCT_ID, nullable = false)
  private Product product;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderDetails.SHIPMENT, referencedColumnName = DbImportMaster.SHIPMENT_NO, nullable = false)
  private ImportMaster shipment;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbOrderDetails.WAREHOUSE, nullable = false)
  private Warehouse warehouse;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderDetails.SALE, referencedColumnName = DbSale.ID)
  private Sale sale;

  @Column(name = DbOrderDetails.VAT)
  private Double vat;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbOrderDetails.UOM, nullable = false)
  private UOM uom;

  @Column(name = DbOrderDetails.QUANTITY, nullable = false)
  private Double quantity;

  @Column(name = DbOrderDetails.DISCOUNT)
  private Double discount;

  @Column(name = DbOrderDetails.REMARKS)
  private String remarks;

  @Column(name = DbOrderDetails.PRICE, nullable = false)
  private Double price;

  @Column(name = DbOrderDetails.TOTAL_PRICE)
  private Double totalPrice;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbOrderDetails.ORDER_STATUS, nullable = false)
  private OrderStatus status;

  @CreationTimestamp
  @Column(name = DbOrderDetails.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;

  @UpdateTimestamp
  @Column(name = DbOrderDetails.LAST_UPDATED_ON)
  private ZonedDateTime updatedOn;
}
