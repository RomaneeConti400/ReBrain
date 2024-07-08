package com.example.rebrain.model;

import com.example.rebrain.entity.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private Integer id;
    private String title;
    private String text;
    private String descr;

    public static Note toModel(NoteEntity entity) {
        Note model = new Note();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setText(entity.getText());
        model.setDescr(entity.getDescr());
        return model;
    }
}
