package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbBranch;
import com.flagship.constant.db.DbConstant.DbCustomer;
import com.flagship.constant.db.DbConstant.DbSupplier;
import com.flagship.constant.db.DbConstant.DbUser;
import com.flagship.constant.enums.CustomerType;
import com.flagship.constant.enums.Status;
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
@Table(name = DbCustomer.TABLE_NAME)
public class Customer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbCustomer.ID)
  private Long id;

  @Column(name = DbCustomer.CUSTOMER_ID, nullable = false, unique = true)
  private String customerId;

  @Column(name = DbCustomer.CUSTOMER_NAME, nullable = false)
  private String customerName;

  @Column(name = DbCustomer.COMPANY)
  private String company;

  @Column(name = DbCustomer.PHONE_NUMBER, nullable = false)
  private String phoneNumber;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbCustomer.STATUS, nullable = false)
  private Status status;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbCustomer.CUSTOMER_TYPE, nullable = false)
  private CustomerType customerType;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbCustomer.SUPPLIER, referencedColumnName = DbSupplier.SUPPLIER_ID)
  private Supplier supplier;

  @Column(name = DbCustomer.ADDRESS)
  private String address;

  @Column(name = DbCustomer.BIN_NO)
  private String binNo;

  @Column(name = DbCustomer.CREDIT_TERM)
  private Integer creditTerm;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbCustomer.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbUser.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbCustomer.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
  private User updatedBy;

  @UpdateTimestamp
  @Column(name = DbCustomer.LAST_UPDATED_ON, nullable = false, updatable = false)
  private ZonedDateTime updatedOn;
}
