package com.example.rebrain.mapper;

import com.example.rebrain.dto.CardsSetsDto;
import com.example.rebrain.entity.CardsSetsEntity;

public class CardsSetsMapper {

    public static CardsSetsEntity toEntity(CardsSetsDto cardsSetsDto) {
        if (cardsSetsDto == null) {
            return null;
        }
        CardsSetsEntity cardsSetsEntity = new CardsSetsEntity();
        cardsSetsEntity.setSetId(cardsSetsDto.getSetId());
        cardsSetsEntity.setCardId(cardsSetsDto.getCardId());
        return cardsSetsEntity;
    }

    public static CardsSetsDto toDto(CardsSetsEntity cardsSetsEntity) {
        if (cardsSetsEntity == null) {
            return null;
        }
        CardsSetsDto cardsSetsDto = new CardsSetsDto();
        cardsSetsDto.setSetId(cardsSetsEntity.getSetId());
        cardsSetsDto.setCardId(cardsSetsEntity.getCardId());
        return cardsSetsDto;
    }
}
