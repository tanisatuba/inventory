package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbProduct;
import com.flagship.constant.db.DbConstant.DbStock;
import com.flagship.constant.enums.UOM;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbStock.TABLE_NAME)
public class Stock implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbStock.ID)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbStock.PRODUCT, referencedColumnName = DbProduct.PRODUCT_ID, nullable = false)
  private Product product;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbStock.UOM, nullable = false)
  private UOM uom;

  @Column(name = DbStock.TOTAL_BUY, nullable = false)
  private Double totalBuy;

  @Column(name = DbStock.TOTAL_SELL)
  private Double totalSell;

  @Column(name = DbStock.IN_STOCK, nullable = false)
  private Double inStock;

  @CreationTimestamp
  @Column(name = DbStock.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;

  @CreationTimestamp
  @Column(name = DbStock.LAST_UPDATED_ON)
  private ZonedDateTime updatedOn;
}
