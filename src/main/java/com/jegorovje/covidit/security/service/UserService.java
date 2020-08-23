package com.jegorovje.covidit.security.service;

import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.security.data.UserDto;
import com.jegorovje.covidit.security.data.entity.UserEntity;
import com.jegorovje.covidit.security.manager.UserRepository;
import com.jegorovje.covidit.security.mapper.UserMapper;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import java.util.Optional;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Singleton
@TransactionalAdvice
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityManager entityManager;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.entityManager = CommandContext.getCommandContext().getEngineConfiguration()
            .getEntityManager();
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    public UserEntity createUser(UserDto userDto) {
        UserEntity user = userMapper.toEntity(userDto);
        entityManager.persist(user);
//        entityManager.flush();
        return user;
    }

    public Optional<UserDto> findUser(String login) {
        String qlString = "from UserEntity where LOWER(login) LIKE LOWER(:login)";
        TypedQuery<UserEntity> query = entityManager.createQuery(qlString, UserEntity.class);
        query.setParameter("login", login);
        return query.getResultList().stream().findFirst().map(userMapper::toDto);
//    return userRepository.findByLogin(login).map(userMapper::toDto);
    }

    public Optional<UserDto> findByRefreshToken(String refreshToken) {
        return userRepository.findByToken(refreshToken).map(userMapper::toDto);
    }

    public void saveRefreshToken(String username, String refreshToken) {
        userRepository.findByLogin(username).ifPresent(
            user -> {
                user.setToken(refreshToken);
                userRepository.update(user);
            }
        );
    }
}
