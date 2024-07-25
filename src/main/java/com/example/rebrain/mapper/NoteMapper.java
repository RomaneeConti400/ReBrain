package com.example.rebrain.mapper;

import com.example.rebrain.dto.NoteDto;
import com.example.rebrain.entity.NoteEntity;

public class NoteMapper {

    public static NoteEntity toEntity(NoteDto noteDto) {
        if (noteDto == null) {
            return null;
        }
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(noteDto.getId());
        noteEntity.setTitle(noteDto.getTitle());
        noteEntity.setText(noteDto.getText());
        noteEntity.setDescription(noteDto.getDescription());
        noteEntity.setUserId(noteDto.getUserId());
        return noteEntity;
    }
    
    public static NoteDto toDto(NoteEntity noteEntity) {
        if (noteEntity == null) {
            return null;
        }
        NoteDto noteDto = new NoteDto();
        noteDto.setId(noteEntity.getId());
        noteDto.setTitle(noteEntity.getTitle());
        noteDto.setText(noteEntity.getText());
        noteDto.setDescription(noteEntity.getDescription());
        noteDto.setUserId(noteEntity.getUserId());
        return noteDto;
    }
}
