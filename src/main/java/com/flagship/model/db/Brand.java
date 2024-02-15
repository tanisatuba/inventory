package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbBrand;
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
@Table(name = DbBrand.TABLE_NAME)
public class Brand implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbBrand.ID)
  private Long Id;

  @Column(name = DbBrand.BRAND_ID, nullable = false)
  private String brandId;

  @Column(name = DbBrand.BRAND_NAME, nullable = false)
  private String brandName;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbBrand.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbBrand.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
