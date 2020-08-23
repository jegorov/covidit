package com.jegorovje.covidit.security.mapper;

import com.jegorovje.covidit.security.data.UserDto;
import com.jegorovje.covidit.security.data.entity.UserEntity;
import javax.inject.Singleton;

@Singleton
public class UserMapper {

    public UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDto.getLogin());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setRole(userDto.getRole());
        return userEntity;
    }

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder().login(userEntity.getLogin()).password(userEntity.getPassword())
            .role(userEntity.getRole()).build();
    }
}
