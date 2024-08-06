package com.example.rebrain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestStatisticsDto {
    private Long userId;
    private Long setId;
    private Long testCount;
    private BigDecimal averageScore;
}
