package com.example.rebrain.model;

import com.example.rebrain.entity.SetEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Set {
    private Integer id;
    private String title;
    private String descr;

    public static Set toModel(SetEntity entity) {
        Set model = new Set();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescr(entity.getDescr());
        return model;
    }
}
