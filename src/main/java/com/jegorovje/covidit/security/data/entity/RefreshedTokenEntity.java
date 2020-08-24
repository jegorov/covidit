package com.jegorovje.covidit.security.data.entity;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.GeneratedValue;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "token")
public class RefreshedTokenEntity {

  @Id @GeneratedValue
  UUID id;

  @NonNull @NotBlank
  String userId;

  @NonNull @NotBlank
  String refreshToken;

  @NonNull
  Boolean revoked;

  @DateCreated @NonNull
  Instant dateCreated;
}
