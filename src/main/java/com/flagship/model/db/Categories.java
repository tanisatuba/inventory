package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbCategories;
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
@Table(name = DbCategories.TABLE_NAME)
public class Categories implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbCategories.ID)
  private Long Id;

  @Column(name = DbCategories.CATEGORY_ID)
  private String categoryId;

  @Column(name = DbCategories.CATEGORY_NAME)
  private String categoryName;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbCategories.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbCategories.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
