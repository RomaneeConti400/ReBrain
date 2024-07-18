package com.example.rebrain.controllers;

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

    @PostMapping("/{setId}")
    public ResponseEntity<TestPostDto> createTest(@PathVariable Integer setId) {
        TestEntity testEntity = new TestEntity();
        testEntity.setSetId(setId);
        TestEntity createdTest = testService.create(testEntity);
        TestPostDto createdTestDto = new TestPostDto();
        createdTestDto.setTestId(createdTest.getId());
        createdTestDto.setSetId(createdTest.getSetId());
        return ResponseEntity.ok(createdTestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable Integer id) {
        log.debug("Fetching test with ID: {}", id);
        TestDto test = TestMapper.toDto(testService.getOne(id));
        log.debug("Test found: {}", test);
        return ResponseEntity.ok(test);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TestDto> updateTest(@PathVariable Integer id, @RequestBody TestDto testDto) {
        log.debug("Updating test with ID: {} with data: {}", id, testDto);
        TestEntity updateEntity = TestMapper.toEntity(testDto);
        TestDto updatedTest = TestMapper.toDto(testService.update(id, updateEntity));
        log.info("Test with ID: {} updated", id);
        return ResponseEntity.ok(updatedTest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Integer id) {
        log.info("Deleting test with ID: {}", id);
        testService.delete(id);
        log.info("Test with ID: {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
