package com.example.rebrain.entity;

import com.example.rebrain.dto.CardDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private Integer id;

    @Column(name = "card_title")
    private String title;

    @Column(name = "card_description")
    private String description;

}
