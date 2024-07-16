package com.example.rebrain.mapper;

import com.example.rebrain.dto.UserDto;
import com.example.rebrain.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setMiddleName(userDto.getMiddleName());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }

    public static UserDto toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setMiddleName(userEntity.getMiddleName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }
}
