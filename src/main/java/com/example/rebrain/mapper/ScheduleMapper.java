package com.example.rebrain.mapper;

import com.example.rebrain.dto.ScheduleDto;
import com.example.rebrain.entity.ScheduleEntity;

public class ScheduleMapper {

    public static ScheduleEntity toEntity(ScheduleDto scheduleDto) {
        if (scheduleDto == null) {
            return null;
        }
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setId(scheduleDto.getId());
        scheduleEntity.setUserId(scheduleDto.getUserId());
        scheduleEntity.setSetId(scheduleDto.getSetId());
        scheduleEntity.setCron(scheduleDto.getCron());
        scheduleEntity.setStartDate(scheduleDto.getStartDate());
        return scheduleEntity;
    }

    public static ScheduleDto toDto(ScheduleEntity scheduleEntity) {
        if (scheduleEntity == null) {
            return null;
        }
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setId(scheduleEntity.getId());
        scheduleDto.setUserId(scheduleEntity.getUserId());
        scheduleDto.setSetId(scheduleEntity.getSetId());
        scheduleDto.setCron(scheduleEntity.getCron());
        scheduleDto.setStartDate(scheduleEntity.getStartDate());
        return scheduleDto;
    }
}
