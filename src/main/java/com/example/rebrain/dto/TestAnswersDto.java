package com.example.rebrain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestAnswersDto {
    private Integer testId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<AnswerDto> answers;
}
