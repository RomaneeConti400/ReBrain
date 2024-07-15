package com.example.rebrain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.rebrain.controllers.CardController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {

    @Autowired
    private CardController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}