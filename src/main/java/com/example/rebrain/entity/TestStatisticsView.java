package com.example.rebrain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "test_statistics_view") // Указываем имя представления
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(TestStatisticsId.class)
public class TestStatisticsView {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "set_id")
    private Long setId;
    @Column(name = "test_count")
    private Long testCount;
    @Column(name = "average_score")
    private BigDecimal averageScore;
}