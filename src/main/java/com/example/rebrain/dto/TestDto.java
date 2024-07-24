package com.example.rebrain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {
    private Long id;
    private Long setId;
    private Long userId;
    private String answers;
    private Integer cardsNumber;
    private Integer wrongAnswers;
    private Integer correctAnswers;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer completionTime;
}
