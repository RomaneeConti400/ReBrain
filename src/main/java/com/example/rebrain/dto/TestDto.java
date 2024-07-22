package com.example.rebrain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {
    private Integer id;
    private Integer setId;
    private Integer userId;
    private String answers;
    private Integer cardsNumber;
    private Integer wrongAnswers;
    private Integer correctAnswers;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer completionTime;
}
