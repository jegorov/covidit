package com.jegorovje.covidit.security.mapper;

import com.jegorovje.covidit.engine.data.entity.impl.UserEntity;
import com.jegorovje.covidit.security.dto.UserDto;
import javax.inject.Singleton;

@Singleton
public class UserMapper {

    public UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setRole(userDto.getRole());
        return userEntity;
    }

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder().username(userEntity.getUsername()).password(userEntity.getPassword())
            .role(userEntity.getRole()).build();
    }
}
