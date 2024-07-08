package com.example.rebrain.Dto;

import com.example.rebrain.entity.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Integer id;
    private String title;
    private String descr;

    public static CardDto toModel(CardEntity entity) {
        CardDto model = new CardDto();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescr(entity.getDescr());
        return model;
    }
}
