package com.example.rebrain.dto;

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
    private String description;

}
