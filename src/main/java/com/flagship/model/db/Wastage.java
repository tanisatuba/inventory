package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbImportMaster;
import com.flagship.constant.db.DbConstant.DbProduct;
import com.flagship.constant.db.DbConstant.DbUser;
import com.flagship.constant.db.DbConstant.DbWastage;
import com.flagship.constant.enums.Cause;
import com.flagship.constant.enums.Warehouse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbWastage.TABLE_NAME)
public class Wastage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbWastage.ID)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbWastage.PRODUCT, referencedColumnName = DbProduct.PRODUCT_ID, nullable = false)
  private Product product;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbWastage.SHIPMENT, referencedColumnName = DbImportMaster.SHIPMENT_NO, nullable = false)
  private ImportMaster shipment;

  @Column(name = DbWastage.CARTOON)
  private Double cartoon;

  @Column(name = DbWastage.PIECE)
  private Double piece;

  @Column(name = DbWastage.KG_LT)
  private Double kgLt;

  @Column(name = DbWastage.CAUSE, nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Cause cause;

  @Column(name = DbWastage.WAREHOUSE, nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Warehouse warehouse;

  @Column(name = DbWastage.SERIAL_NO, nullable = false)
  private Long serialNo;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbWastage.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbUser.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
