package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbCountry;
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
@Table(name = DbCountry.TABLE_NAME)
public class Country implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbCountry.ID)
  private Long Id;

  @Column(name = DbCountry.COUNTRY_ID)
  private String countryId;

  @Column(name = DbCountry.COUNTRY_NAME)
  private String countryName;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbCountry.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbCountry.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
