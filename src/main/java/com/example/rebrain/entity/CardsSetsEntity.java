package com.example.rebrain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.TypeDef;

@Entity
@Table(name = "cardsSets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CardsSetsId.class)
public class CardsSetsEntity {
    @Id
    @Column(name = "card_id")
    private Long cardId;
    @Id
    @Column(name = "set_id")
    private Long setId;
}
