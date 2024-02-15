package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbProduct;
import com.flagship.constant.db.DbConstant.DbSale;
import com.flagship.constant.db.DbConstant.DbSupplier;
import com.flagship.constant.db.DbConstant.DbUser;
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
@Table(name = DbSale.TABLE_NAME)
public class Sale implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbSale.ID)
  private Long Id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbSale.SUPPLIER, referencedColumnName = DbSupplier.SUPPLIER_ID, nullable = false)
  private Supplier supplier;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbSale.PRODUCT, referencedColumnName = DbProduct.PRODUCT_ID, nullable = false)
  private Product product;

  @Column(name = DbSale.ARTICLE, nullable = false)
  private String article;

  @Column(name = DbSale.SALE_CODE, nullable = false)
  private String saleCode;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbSale.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbSale.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
