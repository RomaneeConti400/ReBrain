package com.example.rebrain.util;

import com.example.rebrain.dto.AnswerDto;
import com.example.rebrain.exception.InternalProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JsonConverter {

    private final ObjectMapper objectMapper;

    public String convertAnswersToJson(Object answers) {
        try {
            return objectMapper.writeValueAsString(answers);
        } catch (JsonProcessingException e) {
            log.error("Error converting answers to JSON", e);
            throw new InternalProcessingException("Error converting answers to JSON: " + e.getMessage());
        }
    }
}
