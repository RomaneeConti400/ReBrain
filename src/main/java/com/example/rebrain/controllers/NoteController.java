package com.example.rebrain.controllers;

import com.example.rebrain.dto.NoteDto;
import com.example.rebrain.dto.NoteDto;
import com.example.rebrain.dto.NoteDto;
import com.example.rebrain.dto.UpdateNoteDto;
import com.example.rebrain.entity.NoteEntity;
import com.example.rebrain.entity.NoteEntity;
import com.example.rebrain.entity.NoteEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.mapper.NoteMapper;
import com.example.rebrain.mapper.NoteMapper;
import com.example.rebrain.mapper.NoteMapper;
import com.example.rebrain.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto) {
        try {
            NoteEntity noteEntity = NoteMapper.toEntity(noteDto);
            NoteEntity createdNote = noteService.create(noteEntity);
            NoteDto createdToDto = NoteMapper.toDto(createdNote);
            URI location = URI.create("/notes/" + createdToDto.getId());
            return ResponseEntity.created(location).body(createdToDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        try {
            List<NoteEntity> notesEntities = noteService.getAll();
            List<NoteDto> noteDtos = notesEntities.stream()
                    .map(NoteMapper::toDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(noteDtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Integer id) {
        try {
            NoteDto note = NoteMapper.toDto(noteService.getOne(id));
            return ResponseEntity.ok(note);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Integer id, @RequestBody NoteDto noteDto) {
        try {
            NoteEntity updateEntity = NoteMapper.toEntity(noteDto);
            NoteDto updatedNote = NoteMapper.toDto(noteService.update(id, updateEntity));
            return ResponseEntity.ok(updatedNote);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer id) {
        try {
            noteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
