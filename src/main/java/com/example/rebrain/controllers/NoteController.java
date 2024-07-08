package com.example.rebrain.controllers;

import com.example.rebrain.exception.NoteNotFoundException;
import com.example.rebrain.Dto.UpdateNoteDto;
import lombok.AllArgsConstructor;
import com.example.rebrain.entity.NoteEntity;
import com.example.rebrain.services.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    @PostMapping
    public ResponseEntity createNote(@RequestBody NoteEntity note) {
        try {
            NoteEntity createdNote = noteService.create(note);
            URI location = URI.create("/notes/" + createdNote.getId());
            return ResponseEntity.created(location).body(createdNote);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(noteService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneNote(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(noteService.getOne(id));
        } catch (NoteNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateNote(@PathVariable Integer id, @RequestBody UpdateNoteDto updateNoteDto) {
        try {
            return ResponseEntity.ok(noteService.update(id, updateNoteDto));
        } catch (NoteNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable Integer id) {
        try {
            noteService.delete(id);
            return ResponseEntity.noContent().build();  // Возвращает код 204
        } catch (NoteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  // Возвращает код 404
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
