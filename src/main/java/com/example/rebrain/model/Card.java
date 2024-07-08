package com.example.rebrain.model;

import com.example.rebrain.entity.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Integer id;
    private String title;
    private String descr;

    public static Card toModel(CardEntity entity) {
        Card model = new Card();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescr(entity.getDescr());
        return model;
    }
}
