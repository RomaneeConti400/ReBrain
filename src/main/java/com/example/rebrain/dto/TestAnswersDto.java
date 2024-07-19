package com.example.rebrain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestAnswersDto {
    private Integer testId;
    private List<AnswerDto> answers;
}
