package com.example.rebrain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String startDate;
    private Integer completionTime;
}
