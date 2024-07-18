package com.example.rebrain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "set_id")
    private Integer setId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "answers")
    private String answers;

    @Column(name = "cards_number")
    private Integer cardsNumber;

    @Column(name = "wrong_answers")
    private Integer wrongAnswers;

    @Column(name = "correct_answers")
    private Integer correctAnswers;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "completion_time")
    private Integer completionTime;
}
