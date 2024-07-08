package com.example.rebrain.Dto;

import com.example.rebrain.entity.SetEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetDto {
    private Integer id;
    private String title;
    private String descr;

    public static SetDto toModel(SetEntity entity) {
        SetDto model = new SetDto();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setDescr(entity.getDescr());
        return model;
    }
}
