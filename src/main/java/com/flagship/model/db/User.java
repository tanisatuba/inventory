package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbUser;
import com.flagship.constant.enums.Gender;
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
@Table(name = DbUser.TABLE_NAME)
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbUser.ID)
  private Long id;

  @Column(name = DbUser.NAME, nullable = false)
  private String name;

  @Column(name = DbUser.EMAIL, nullable = false, unique = true)
  private String email;

  @Column(name = DbUser.PASSWORD, nullable = false)
  private String password;

  @Column(name = DbUser.DATE_OF_BIRTH, nullable = false)
  private ZonedDateTime dateOfBirth;

  @Column(name = DbUser.GENDER, nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Gender gender;

  @CreationTimestamp
  @Column(name = DbUser.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
