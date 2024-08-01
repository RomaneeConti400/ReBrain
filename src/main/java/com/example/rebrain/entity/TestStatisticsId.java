package com.example.rebrain.entity;

import java.io.Serializable;

public class TestStatisticsId implements Serializable {
    private Long userId;
    private Long setId;

    // Конструкторы
    public TestStatisticsId() {}

    public TestStatisticsId(Long userId, Long setId) {
        this.userId = userId;
        this.setId = setId;
    }
}