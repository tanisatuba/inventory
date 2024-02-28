package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.*;
import com.flagship.constant.enums.ChallanCompany;
import com.flagship.constant.enums.CustomerType;
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
@Table(name = DbOrderMaster.TABLE_NAME)
public class OrderMaster implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbOrderMaster.ID)
  private Long id;

  @Column(name = DbOrderMaster.ORDER_ID, nullable = false)
  private Long orderId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderMaster.CUSTOMER, referencedColumnName = DbCustomer.CUSTOMER_ID, nullable = false)
  private Customer customer;

  @Column(name = DbOrderMaster.COMPANY_NAME)
  private String companyName;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderMaster.SUPPLIER, referencedColumnName = DbSupplier.SUPPLIER_ID)
  private Supplier supplier;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderMaster.BRANCH, referencedColumnName = DbBranch.ID)
  private Branch branch;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbOrderMaster.CUSTOMER_TYPE, nullable = false)
  private CustomerType customerType;

  @Column(name = DbOrderMaster.ORDER_DATE, nullable = false)
  private ZonedDateTime orderDate;

  @Column(name = DbOrderMaster.DELIVERY_DATE, nullable = false)
  private ZonedDateTime deliveryDate;

  @Column(name = DbOrderMaster.CREDIT_TERM)
  private Integer creditTerm;

  @Enumerated(EnumType.STRING)
  @Column(name = DbOrderMaster.CHALLAN_COMPANY)
  private ChallanCompany challanCompany;

  @Column(name = DbOrderMaster.CHALLAN)
  private Integer challan;

  @Column(name = DbOrderMaster.ADDRESS, nullable = false)
  private String address;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderMaster.ORDER_BY, referencedColumnName = DbSalesPerson.SALES_PERSON_ID, nullable = false)
  private SalesPerson orderBy;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderMaster.CREATED_BY, referencedColumnName = DbUser.EMAIL, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbOrderDetails.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderDetails.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
  private User updatedBy;

  @UpdateTimestamp
  @Column(name = DbOrderDetails.LAST_UPDATED_ON, nullable = false, updatable = false)
  private ZonedDateTime updatedOn;
}
