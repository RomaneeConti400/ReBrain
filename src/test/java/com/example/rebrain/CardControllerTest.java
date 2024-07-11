package com.example.rebrain;

import com.example.rebrain.dto.CardDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCard() throws Exception {
        CardDto testCardDto = new CardDto();
        testCardDto.setTitle("Test Card");
        testCardDto.setDescription("Test Description");

        // Конвертируем CardDto в JSON
        String jsonRequest = objectMapper.writeValueAsString(testCardDto);

        // Отправляем POST запрос на создание карточки
        ResultActions result = mockMvc.perform(post("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        // Проверяем статус код и ожидаемый результат
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(testCardDto.getTitle()))
                .andExpect(jsonPath("$.description").value(testCardDto.getDescription()));
    }

    @Test
    public void testGetCardById() throws Exception {
        // Создаем карточку для теста
        CardDto createdCard = createTestCard("Test Card", "Test Description");

        // Отправляем GET запрос на получение карточки по ID
        ResultActions result = mockMvc.perform(get("/cards/{id}", createdCard.getId()));

        // Проверяем статус код и ожидаемый результат
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(createdCard.getTitle()))
                .andExpect(jsonPath("$.description").value(createdCard.getDescription()));
    }

    @Test
    public void testUpdateCard() throws Exception {
        // Создаем карточку для теста
        CardDto createdCard = createTestCard("Test Card", "Test Description");

        // Обновляем данные карточки
        String updatedTitle = "Updated Title";
        createdCard.setTitle(updatedTitle);
        String jsonRequest = objectMapper.writeValueAsString(createdCard);

        // Отправляем PATCH запрос на обновление карточки по ID
        ResultActions result = mockMvc.perform(patch("/cards/{id}", createdCard.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        // Проверяем статус код и ожидаемый результат
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updatedTitle));
    }

    @Test
    public void testDeleteCard() throws Exception {
        // Создаем карточку для теста
        CardDto createdCard = createTestCard("Test Card", "Test Description");

        // Отправляем DELETE запрос на удаление карточки по ID
        ResultActions result = mockMvc.perform(delete("/cards/{id}", createdCard.getId()));

        // Проверяем статус код
        result.andExpect(status().isNoContent());

        // Проверяем, что карточка действительно удалена
        mockMvc.perform(get("/cards/{id}", createdCard.getId()))
                .andExpect(status().isNotFound());
    }

    // Вспомогательный метод для создания карточки и получения её DTO
    private CardDto createTestCard(String title, String description) throws Exception {
        CardDto testCardDto = new CardDto();
        testCardDto.setTitle(title);
        testCardDto.setDescription(description);

        String jsonRequest = objectMapper.writeValueAsString(testCardDto);
        String responseJson = mockMvc.perform(post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        return objectMapper.readValue(responseJson, CardDto.class);
    }
}
