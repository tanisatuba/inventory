package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbSalesPerson;
import com.flagship.constant.db.DbConstant.DbUser;
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
@Table(name = DbSalesPerson.TABLE_NAME)
public class SalesPerson implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbSalesPerson.ID)
  private Long id;

  @Column(name = DbSalesPerson.SALES_PERSON_ID, nullable = false, unique = true)
  private String salesPersonId;

  @Column(name = DbSalesPerson.SALES_PERSON_NAME, nullable = false)
  private String salesPersonName;

  @Column(name = DbSalesPerson.PHONE_NUMBER, nullable = false)
  private String phoneNumber;

  @Column(name = DbSalesPerson.AREA)
  private String area;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbSalesPerson.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbSalesPerson.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbSalesPerson.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
  private User updatedBy;

  @UpdateTimestamp
  @Column(name = DbSalesPerson.LAST_UPDATED_ON)
  private ZonedDateTime updatedOn;
}
