package com.example.rebrain.Dto;

import com.example.rebrain.entity.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    private Integer id;
    private String title;
    private String text;
    private String descr;

    public static NoteDto toModel(NoteEntity entity) {
        NoteDto model = new NoteDto();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setText(entity.getText());
        model.setDescr(entity.getDescr());
        return model;
    }
}
