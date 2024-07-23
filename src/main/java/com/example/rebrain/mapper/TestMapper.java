package com.example.rebrain.mapper;

import com.example.rebrain.dto.TestDto;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.entity.TestEntity;

import java.time.LocalDateTime;

public class TestMapper {

    public static TestEntity toEntity(TestDto testDto) {
        if (testDto == null) {
            return null;
        }
        TestEntity testEntity = new TestEntity();
        testEntity.setId(testDto.getId());
        testEntity.setSetId(testDto.getSetId());
        testEntity.setUserId(testDto.getUserId());
        testEntity.setAnswers(testDto.getAnswers());
        testEntity.setCardsNumber(testDto.getCardsNumber());
        testEntity.setWrongAnswers(testDto.getWrongAnswers());
        testEntity.setCorrectAnswers(testDto.getCorrectAnswers());
        testEntity.setStartDate(testDto.getStartDate());
        testEntity.setEndDate(testDto.getEndDate());
        testEntity.setCompletionTime(testDto.getCompletionTime());
        return testEntity;
    }

    public static TestDto toDto(TestEntity testEntity) {
        if (testEntity == null) {
            return null;
        }
        return new TestDto(
                testEntity.getId(),
                testEntity.getSetId(),
                testEntity.getUserId(),
                testEntity.getAnswers(),
                testEntity.getCardsNumber(),
                testEntity.getWrongAnswers(),
                testEntity.getCorrectAnswers(),
                testEntity.getStartDate(),
                testEntity.getEndDate(),
                testEntity.getCompletionTime()
        );
    }
}

