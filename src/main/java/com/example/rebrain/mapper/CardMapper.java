package com.example.rebrain.mapper;

import com.example.rebrain.dto.CardDto;
import com.example.rebrain.entity.CardEntity;

public class CardMapper {

    public static CardEntity toEntity(CardDto cardDto) {
        if (cardDto == null) {
            return null;
        }
        CardEntity cardEntity = new CardEntity();
        cardEntity.setId(cardDto.getId());
        cardEntity.setTitle(cardDto.getTitle());
        cardEntity.setDescription(cardDto.getDescription());
        return cardEntity;
    }

    public static CardDto toDto(CardEntity cardEntity) {
        if (cardEntity == null) {
            return null;
        }
        CardDto cardDto = new CardDto();
        cardDto.setId(cardEntity.getId());
        cardDto.setTitle(cardEntity.getTitle());
        cardDto.setDescription(cardEntity.getDescription());
        return cardDto;
    }
}
