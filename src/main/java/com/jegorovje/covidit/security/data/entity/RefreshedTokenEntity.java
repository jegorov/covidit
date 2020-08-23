package com.jegorovje.covidit.security.data.entity;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import java.time.Instant;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@MappedEntity
@Data
@NoArgsConstructor
@Table(name = "token")
public class RefreshedTokenEntity {

  @Id
  @GeneratedValue
  @NonNull
  Long id;

  @NonNull
  @NotBlank
  String userId;

  @NonNull
  @NotBlank
  String refreshToken;

  @NonNull

  Boolean revoked;

  @DateCreated
  @NonNull

  Instant dateCreated;
}
