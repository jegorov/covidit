package com.jegorovje.covidit.security.manager;

import com.jegorovje.covidit.security.data.entity.RefreshedTokenEntity;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.NonNull;

@TransactionalAdvice
public interface RefreshTokenRepository extends CrudRepository<RefreshedTokenEntity, Long> {

  RefreshedTokenEntity save(@NonNull @NotBlank String username,
      @NonNull @NotBlank String refreshToken,
      @NonNull @NotNull Boolean revoked);

  Optional<RefreshedTokenEntity> findByRefreshToken(@NonNull @NotBlank String refreshToken);

  long updateByUsername(@NonNull @NotBlank String username,
      @NonNull @NotNull Boolean revoked);
}
