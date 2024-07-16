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
        userEntity.setFirst_name(userDto.getFirst_name());
        userEntity.setLast_name(userDto.getLast_name());
        userEntity.setMiddle_name(userDto.getMiddle_name());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }

    public static UserDto toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFirst_name(userEntity.getFirst_name());
        userDto.setMiddle_name(userEntity.getMiddle_name());
        userDto.setLast_name(userEntity.getLast_name());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }
}
