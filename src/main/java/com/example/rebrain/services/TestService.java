package com.example.rebrain.services;

import com.example.rebrain.dto.AnswerDto;
import com.example.rebrain.dto.TestAnswersDto;
import com.example.rebrain.dto.TestDto;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.entity.TestEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.mapper.CardMapper;
import com.example.rebrain.mapper.TestMapper;
import com.example.rebrain.repositories.CardRepo;
import com.example.rebrain.repositories.TestRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {

    private final TestRepo testRepo;
    private final CardRepo cardRepo;

    public TestEntity create(TestEntity testEntity) {
        log.debug("Saving new test: {}", testEntity);
        TestEntity savedEntity = testRepo.save(testEntity);
        log.info("Test saved with ID: {}", savedEntity.getId());
        return savedEntity;
    }

    public TestEntity finishTest(TestAnswersDto testAnswersDto) {
        List<AnswerDto> answers = testAnswersDto.getAnswers();
        int correctAnswers = 0;
        int wrongAnswers = 0;

        for (AnswerDto answer : answers) {
            CardEntity card = cardRepo.findById(answer.getCardId()).orElse(null);
            if (card != null && card.getTitle().equals(answer.getAnswer())) {
                correctAnswers++;
            } else {
                wrongAnswers++;
            }
        }
        LocalDateTime startDate = testAnswersDto.getStartDate();
        LocalDateTime endDate = testAnswersDto.getEndDate();
        TestEntity testEntity = getEntityById(testAnswersDto.getTestId());
        testEntity.setId(testAnswersDto.getTestId());
        testEntity.setStartDate(startDate);
        testEntity.setEndDate(endDate);
        testEntity.setCompletionTime((int) Duration.between(startDate, endDate).getSeconds());
        testEntity.setCorrectAnswers(correctAnswers);
        testEntity.setWrongAnswers(wrongAnswers);
        testEntity.setCardsNumber(answers.size());
        testRepo.save(testEntity);
        return testEntity;
    }

    public TestEntity getOne(Integer id) {
        log.debug("Fetching test with ID: {}", id);
        return testRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Test with ID: {} not found", id);
                    return new ObjectNotFoundException("Test with ID " + id + " not found");
                });
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