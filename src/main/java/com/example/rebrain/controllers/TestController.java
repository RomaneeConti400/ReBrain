package com.example.rebrain.controllers;

import com.example.rebrain.dto.*;
import com.example.rebrain.entity.TestEntity;
import com.example.rebrain.entity.TestStatisticsView;
import com.example.rebrain.mapper.TestMapper;
import com.example.rebrain.mapper.TestStatisticsMapper;
import com.example.rebrain.services.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity<TestPostDto> createTest(@RequestBody TestPostDto testPostDto) {
        TestEntity testEntity = new TestEntity();
        testEntity.setSetId(testPostDto.getSetId());
        TestEntity createdTest = testService.create(testEntity);
        TestPostDto createdTestDto = new TestPostDto(createdTest.getId(), createdTest.getSetId());
        return ResponseEntity.ok(createdTestDto);
    }

    @PostMapping("/{testId}/answers")
    public ResponseEntity<TestDto> finishTest(@RequestBody TestAnswersDto testAnswersDto) {
        TestEntity result = testService.finishTest(testAnswersDto);
        TestDto resultToDto = TestMapper.toDto(result);
        return ResponseEntity.ok(resultToDto);
    }

    @PostMapping("/statistics/_search")
    public ResponseEntity<TestStatisticsDto> searchTestStatistics(@RequestBody TestStatisticsGetDto testStatisticsGetDto) {
        TestStatisticsView statisticsView = testService.getTestStatistics(testStatisticsGetDto.getUserId(), testStatisticsGetDto.getSetId());
        TestStatisticsDto statisticsDto = TestStatisticsMapper.toDto(statisticsView);
        return ResponseEntity.ok(statisticsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable Long id) {
        log.debug("Fetching test with ID: {}", id);
        TestDto test = TestMapper.toDto(testService.getOne(id));
        log.debug("Test found: {}", test);
        return ResponseEntity.ok(test);
    }
}
