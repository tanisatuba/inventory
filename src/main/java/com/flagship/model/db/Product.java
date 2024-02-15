package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbProduct;
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
@Table(name = DbProduct.TABLE_NAME)
public class Product implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbProduct.ID)
  private Long id;

  @Column(name = DbProduct.PRODUCT_ID, nullable = false, unique = true)
  private String productId;

  @Column(name = DbProduct.PRODUCT_NAME, nullable = false, unique = true)
  private String productName;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbProduct.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbProduct.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
