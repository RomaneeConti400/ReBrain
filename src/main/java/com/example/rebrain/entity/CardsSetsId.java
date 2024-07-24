package com.example.rebrain.entity;

import java.io.Serializable;
import java.util.Objects;

public class CardsSetsId implements Serializable {
    private Long cardId;
    private Long setId;

    // Конструкторы
    public CardsSetsId() {}

    public CardsSetsId(Long cardId, Long setId) {
        this.cardId = cardId;
        this.setId = setId;
    }
}