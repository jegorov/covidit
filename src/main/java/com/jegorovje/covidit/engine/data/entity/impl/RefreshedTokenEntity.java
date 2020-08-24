package com.jegorovje.covidit.engine.data.entity.impl;

import com.jegorovje.covidit.engine.data.entity.Entity;
import io.micronaut.data.annotation.DateCreated;
import java.time.Instant;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@javax.persistence.Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "token")
public class RefreshedTokenEntity extends Entity {

  @NonNull @NotBlank
  String username;

  @NonNull @NotBlank
  String refreshToken;

  @NonNull
  Boolean revoked;

  @DateCreated @NonNull
  Instant dateCreated;
}
