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
public class TestStatisticsView {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "set_id")
    private Long setId;
    @Column(name = "test_count")
    private Long testCount;
    @Column(name = "average_score")
    private BigDecimal averageScore;
}