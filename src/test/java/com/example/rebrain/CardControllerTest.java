package com.example.rebrain;

import com.example.rebrain.dto.CardDto;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.repositories.CardRepo;
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

import static com.example.rebrain.mapper.CardMapper.toDto;
import static com.example.rebrain.mapper.CardMapper.toEntity;
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

    @Autowired
    private CardRepo cardRepo;

    @Test
    public void createCard() throws Exception {
        CardDto testCardDto = new CardDto();
        testCardDto.setTitle("Test Card");
        testCardDto.setDescription("Test Description");

        String jsonRequest = objectMapper.writeValueAsString(testCardDto);

        ResultActions result = mockMvc.perform(post("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        result.andExpect(status().isInternalServerError());
    }

    @Test
    public void getCardById() throws Exception {
        CardEntity createdCard = createTestCard("Test Card", "Test Description");

        ResultActions result = mockMvc.perform(get("/cards/{id}", createdCard.getId()));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(createdCard.getTitle()))
                .andExpect(jsonPath("$.description").value(createdCard.getDescription()));
    }

    @Test
    public void updateCard() throws Exception {
        CardEntity createdCard = createTestCard("Test Card", "Test Description");

        String updatedTitle = "Updated Title";
        CardDto updatedCardDto = toDto(createdCard);
        updatedCardDto.setTitle(updatedTitle);
        String jsonRequest = objectMapper.writeValueAsString(updatedCardDto);

        ResultActions result = mockMvc.perform(patch("/cards/{id}", createdCard.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updatedTitle));
    }

    @Test
    public void deleteCard() throws Exception {
        CardEntity createdCard = createTestCard("Test Card", "Test Description");

        ResultActions result = mockMvc.perform(delete("/cards/{id}", createdCard.getId()));

        result.andExpect(status().isNoContent());

        mockMvc.perform(get("/cards/{id}", createdCard.getId()))
                .andExpect(status().isNotFound());
    }

    private CardEntity createTestCard(String title, String description) {
        CardDto testCardDto = new CardDto();
        testCardDto.setTitle(title);
        testCardDto.setDescription(description);
        return cardRepo.save(toEntity(testCardDto));
    }
}
