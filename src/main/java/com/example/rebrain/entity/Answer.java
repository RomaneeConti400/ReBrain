package com.example.rebrain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Column(name = "title")
    private Integer cardId;

    @Column(name = "description")
    private String answer;
}
