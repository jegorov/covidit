package com.jegorovje.covidit.security.service;

import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.engine.data.entity.impl.UserEntity;
import com.jegorovje.covidit.security.dto.UserDto;
import com.jegorovje.covidit.security.mapper.UserMapper;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import java.util.Optional;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Singleton
@TransactionalAdvice
public class UserService {

  private final UserMapper userMapper;
  private final EntityManager entityManager;

  public UserService(UserMapper userMapper) {
    this.entityManager = CommandContext.getCommandContext().getEngineConfiguration()
        .getEntityManager();
    this.userMapper = userMapper;
  }


  public UserEntity createUser(UserDto userDto) {
    UserEntity user = userMapper.toEntity(userDto);
    entityManager.persist(user);
    return user;
  }

  public Optional<UserDto> findUser(String username) {
    String qlString = "from UserEntity where LOWER(username) LIKE LOWER(:username)";
    TypedQuery<UserEntity> query = entityManager.createQuery(qlString, UserEntity.class);
    query.setParameter("username", username);
    return query.getResultList().stream().findFirst().map(userMapper::toDto);
  }

  public Optional<UserDto> findByRefreshToken(String refreshToken) {
    String qlString = "from UserEntity where LOWER(token) LIKE LOWER(:token)";
    TypedQuery<UserEntity> query = entityManager.createQuery(qlString, UserEntity.class);
    query.setParameter("token", refreshToken);
    return query.getResultList().stream().findFirst().map(userMapper::toDto);
  }

  public void saveRefreshToken(String username, String refreshToken) {
    String qlString = "from UserEntity where LOWER(username) LIKE LOWER(:username)";
    TypedQuery<UserEntity> query = entityManager.createQuery(qlString, UserEntity.class);
    query.setParameter("username", username);
    UserEntity userEntity = query.getSingleResult();
    if (query.getSingleResult() != null) {
      userEntity.setToken(refreshToken);
      entityManager.refresh(userEntity);
    }
  }
}
