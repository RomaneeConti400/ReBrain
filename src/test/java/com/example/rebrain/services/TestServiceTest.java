package com.example.rebrain.services;

import com.example.rebrain.dto.AnswerDto;
import com.example.rebrain.dto.TestAnswersDto;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.entity.TestEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.CardRepo;
import com.example.rebrain.repositories.CardsSetsRepo;
import com.example.rebrain.repositories.TestRepo;
import com.example.rebrain.util.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @Mock
    private CardsSetsRepo cardsSetsRepo;

    @Mock
    private TestRepo testRepo;

    @Mock
    private CardRepo cardRepo;

    @Mock
    private JsonConverter jsonConverter;

    @InjectMocks
    private TestService testService;

    private TestAnswersDto testAnswersDto;
    private CardEntity cardEntity;
    private TestEntity testEntity;

    @BeforeEach
    void setUp() {
        cardEntity = new CardEntity();
        cardEntity.setId(1);
        cardEntity.setTitle("Correct Answer");

        AnswerDto answerDto = new AnswerDto();
        answerDto.setCardId(1);
        answerDto.setAnswer("Correct Answer");

        testAnswersDto = new TestAnswersDto();
        testAnswersDto.setTestId(1);
        testAnswersDto.setStartDate(LocalDateTime.now().minusMinutes(10));
        testAnswersDto.setEndDate(LocalDateTime.now());
        testAnswersDto.setAnswers(Collections.singletonList(answerDto));

        testEntity = new TestEntity();
        testEntity.setId(1);
    }

    @Test
    void finishTest_successful() throws Exception {
        when(cardRepo.findById(1)).thenReturn(Optional.of(cardEntity));
        when(testRepo.findById(1)).thenReturn(Optional.of(testEntity));
        when(jsonConverter.convertAnswersToJson(any())).thenReturn("json");

        TestEntity result = testService.finishTest(testAnswersDto);

        assertNotNull(result);
        assertEquals(1, result.getCorrectAnswers());
        assertEquals(0, result.getWrongAnswers());
        assertEquals("json", result.getAnswers());

        verify(testRepo, times(1)).save(any(TestEntity.class));
    }

    @Test
    void finishTest_cardNotFound() {
        when(cardRepo.findById(1)).thenReturn(Optional.empty());
        when(testRepo.findById(1)).thenReturn(Optional.of(testEntity));

        TestEntity result = testService.finishTest(testAnswersDto);

        assertNotNull(result);
        assertEquals(0, result.getCorrectAnswers());
        assertEquals(1, result.getWrongAnswers());

        verify(testRepo, times(1)).save(any(TestEntity.class));
    }

    @Test
    void finishTest_testNotFound() {
        when(testRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> testService.finishTest(testAnswersDto));
    }
}
