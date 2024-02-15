package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.*;
import com.flagship.constant.enums.UOM;
import com.flagship.constant.enums.Warehouse;
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
@Table(name = DbImportDetails.TABLE_NAME)
public class ImportDetails implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbImportDetails.ID)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportDetails.SHIPMENT_NO, referencedColumnName = DbImportMaster.SHIPMENT_NO,
          nullable = false)
  private ImportMaster importMaster;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportDetails.PRODUCT, referencedColumnName = DbProduct.PRODUCT_ID, nullable = false)
  private Product product;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportDetails.CATEGORY, referencedColumnName = DbCategories.CATEGORY_ID, nullable = false)
  private Categories categories;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportDetails.BRAND, referencedColumnName = DbBrand.BRAND_ID, nullable = false)
  private Brand brand;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportDetails.COUNTRY, referencedColumnName = DbCountry.COUNTRY_ID, nullable = false)
  private Country country;

  @Column(name = DbImportDetails.PRODUCTION)
  private ZonedDateTime production;

  @Column(name = DbImportDetails.WAREHOUSE, nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Warehouse warehouse;

  @Column(name = DbImportDetails.EXPIRE)
  private ZonedDateTime expire;

  @Column(name = DbImportDetails.CARTOON)
  private Double cartoon;

  @Column(name = DbImportDetails.UNIT_CARTOON)
  private Double unitCartoon;

  @Column(name = DbImportDetails.PIECE)
  private Double piece;

  @Column(name = DbImportDetails.UNIT_PIECE)
  private Double unitPiece;

  @Column(name = DbImportDetails.KG_LT)
  private Double kgLt;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbImportDetails.UOM, nullable = false)
  private UOM uom;

  @Column(name = DbImportDetails.UNIT_PRICE)
  private Double price;

  @Column(name = DbImportDetails.TOTAL_PRICE)
  private Double total;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportDetails.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbImportDetails.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbImportDetails.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
  private User updatedBy;

  @UpdateTimestamp
  @Column(name = DbImportDetails.LAST_UPDATED_ON)
  private ZonedDateTime updatedOn;
}
