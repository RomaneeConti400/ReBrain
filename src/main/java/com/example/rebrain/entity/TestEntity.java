package com.example.rebrain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "set_id")
    private Long setId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "answers")
    private String answers;

    @Column(name = "cards_number")
    private Integer cardsNumber;

    @Column(name = "wrong_answers")
    private Integer wrongAnswers;

    @Column(name = "correct_answers")
    private Integer correctAnswers;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Column(name = "completion_time")
    private Integer completionTime;
}
