package com.jegorovje.covidit.security.manager;

import com.jegorovje.covidit.security.data.entity.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import java.util.Optional;
import java.util.UUID;

@Repository
@TransactionalAdvice
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
  Optional<UserEntity> findByLogin(String login);
  Optional<UserEntity> findByToken(String token);

}
