package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbRequisition;
import com.flagship.constant.db.DbConstant.DbUser;
import com.flagship.constant.enums.Warehouse;
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
@Table(name = DbRequisition.TABLE_NAME)
public class Requisition implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbRequisition.ID)
  private Long Id;

  @Column(name = DbRequisition.PRODUCT)
  private String product;

  @Column(name = DbRequisition.QUANTITY)
  private String quantity;

  @Column(name = DbRequisition.PIECE)
  private Double piece;

  @Column(name = DbRequisition.DELIVERY_MAN)
  private String deliveryMan;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbRequisition.WAREHOUSE, nullable = false)
  private Warehouse warehouse;

  @Column(name = DbRequisition.SERIAL_NO)
  private Long serialNo;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbRequisition.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbRequisition.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
