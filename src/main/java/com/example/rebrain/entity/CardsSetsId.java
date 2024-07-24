package com.example.rebrain.entity;

import java.io.Serializable;
import java.util.Objects;

public class CardsSetsId implements Serializable {
    private Integer cardId;
    private Integer setId;

    // Конструкторы
    public CardsSetsId() {}

    public CardsSetsId(Integer cardId, Integer setId) {
        this.cardId = cardId;
        this.setId = setId;
    }
}