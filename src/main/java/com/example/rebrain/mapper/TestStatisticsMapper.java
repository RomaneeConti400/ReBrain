package com.example.rebrain.mapper;

import com.example.rebrain.dto.TestStatisticsDto;
import com.example.rebrain.entity.TestStatisticsView;

public class TestStatisticsMapper {

    public static TestStatisticsView toEntity(TestStatisticsDto testStatisticsDto) {
        if (testStatisticsDto == null) {
            return null;
        }
        TestStatisticsView testStatisticsView = new TestStatisticsView();
        testStatisticsView.setSetId(testStatisticsDto.getSetId());
        testStatisticsView.setUserId(testStatisticsDto.getUserId());
        testStatisticsView.setTestCount(testStatisticsDto.getTestCount());
        testStatisticsView.setAverageScore(testStatisticsDto.getAverageScore());
        return testStatisticsView;
    }

    public static TestStatisticsDto toDto(TestStatisticsView testStatisticsView) {
        if (testStatisticsView == null) {
            return null;
        }
        return new TestStatisticsDto(
                testStatisticsView.getSetId(),
                testStatisticsView.getUserId(),
                testStatisticsView.getTestCount(),
                testStatisticsView.getAverageScore()
        );
    }
}

