package com.example.rebrain.controllers;

import com.example.rebrain.dto.TestAnswersDto;
import com.example.rebrain.dto.TestDto;
import com.example.rebrain.dto.TestPostDto;
import com.example.rebrain.entity.TestEntity;
import com.example.rebrain.mapper.TestMapper;
import com.example.rebrain.services.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable Long id) {
        log.debug("Fetching test with ID: {}", id);
        TestDto test = TestMapper.toDto(testService.getOne(id));
        log.debug("Test found: {}", test);
        return ResponseEntity.ok(test);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        log.info("Deleting test with ID: {}", id);
        testService.delete(id);
        log.info("Test with ID: {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
