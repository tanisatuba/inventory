package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbOrderBills;
import com.flagship.constant.db.DbConstant.DbOrderMaster;
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
@Table(name = DbOrderBills.TABLE_NAME)
@ToString
public class OrderBills implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbOrderBills.ID)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbOrderBills.ORDER, referencedColumnName = DbOrderMaster.ORDER_ID, nullable = false)
  private OrderMaster order;

  @Column(name = DbOrderBills.SALES, nullable = false)
  private Double sales;

  @Column(name = DbOrderBills.DUE)
  private Double due;

  @Column(name = DbOrderBills.PAYMENT)
  private Double payment;

  @CreationTimestamp
  @Column(name = DbOrderBills.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;

  @UpdateTimestamp
  @Column(name = DbOrderBills.LAST_UPDATED_ON)
  private ZonedDateTime updatedOn;
}
