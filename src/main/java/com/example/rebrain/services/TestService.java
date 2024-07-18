package com.example.rebrain.services;

import com.example.rebrain.entity.TestEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.TestRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {

    private final TestRepo testRepo;

    public TestEntity create(TestEntity testEntity) {
        log.debug("Saving new test: {}", testEntity);
        TestEntity savedEntity = testRepo.save(testEntity);
        log.info("Test saved with ID: {}", savedEntity.getId());
        return savedEntity;
    }

    public List<TestEntity> getAll() {
        log.debug("Fetching all tests");
        return testRepo.findAll();
    }

    public TestEntity getOne(Integer id) {
        log.debug("Fetching test with ID: {}", id);
        return testRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Test with ID: {} not found", id);
                    return new ObjectNotFoundException("Test with ID " + id + " not found");
                });
    }

    public TestEntity update(Integer id, TestEntity updateEntity) {
        log.debug("Updating test with ID: {} with data: {}", id, updateEntity);
        TestEntity testEntity = getEntityById(id);
        TestEntity updatedEntity = testRepo.save(testEntity);
        log.info("Test with ID: {} updated", id);
        return updatedEntity;
    }

    public void delete(Integer id) {
        log.debug("Deleting test with ID: {}", id);
        TestEntity testEntity = getEntityById(id);
        testRepo.delete(testEntity);
        log.info("Test with ID: {} deleted", id);
    }

    private TestEntity getEntityById(Integer id) {
        log.debug("Fetching test entity with ID: {}", id);
        return testRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Test entity with ID: {} not found", id);
                    return new ObjectNotFoundException("Test with ID " + id + " not found");
                });
    }
}