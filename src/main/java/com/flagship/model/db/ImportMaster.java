package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbCountry;
import com.flagship.constant.db.DbConstant.DbImportMaster;
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
@Table(name = DbImportMaster.TABLE_NAME)
public class ImportMaster implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbImportMaster.ID)
  private Long id;

  @Column(name = DbImportMaster.SHIPMENT_NO, nullable = false)
  private String shipmentNo;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportMaster.COUNTRY, referencedColumnName = DbCountry.COUNTRY_ID, nullable = false)
  private Country country;

  @Column(name = DbImportMaster.DATE, nullable = false)
  private ZonedDateTime date;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportMaster.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbImportMaster.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportMaster.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
  private User updatedBy;

  @UpdateTimestamp
  @Column(name = DbImportMaster.LAST_UPDATED_ON)
  private ZonedDateTime updatedOn;
}
