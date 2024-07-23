package com.example.rebrain.mapper;

import com.example.rebrain.dto.SetDto;
import com.example.rebrain.entity.SetEntity;

public class SetMapper {

    public static SetEntity toEntity(SetDto setDto) {
        if (setDto == null) {
            return null;
        }
        SetEntity setEntity = new SetEntity();
        setEntity.setId(setDto.getId());
        setEntity.setTitle(setDto.getTitle());
        setEntity.setDescription(setDto.getDescription());
        return setEntity;
    }

    public static SetDto toDto(SetEntity setEntity) {
        if (setEntity == null) {
            return null;
        }
        SetDto setDto = new SetDto();
        setDto.setId(setEntity.getId());
        setDto.setTitle(setEntity.getTitle());
        setDto.setDescription(setEntity.getDescription());
        return setDto;
    }
}
