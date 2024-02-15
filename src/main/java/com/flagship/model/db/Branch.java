package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbBranch;
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
@Table(name = DbBranch.TABLE_NAME)
public class Branch implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbBranch.ID)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbBranch.SUPPLIER, referencedColumnName = DbSupplier.SUPPLIER_ID, nullable = false)
  private Supplier supplier;

  @Column(name = DbBranch.BRANCH_NAME, nullable = false)
  private String branchName;

  @Column(name = DbBranch.BRANCH_ADDRESS, nullable = false)
  private String address;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbBranch.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbBranch.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
