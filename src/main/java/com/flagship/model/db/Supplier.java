package com.flagship.model.db;

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
@Table(name = DbSupplier.TABLE_NAME)
public class Supplier implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbSupplier.ID)
  private Long id;

  @Column(name = DbSupplier.SUPPLIER_ID, nullable = false)
  private String supplierId;

  @Column(name = DbSupplier.SUPPLIER_NAME, nullable = false)
  private String supplierName;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbSupplier.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbSupplier.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
