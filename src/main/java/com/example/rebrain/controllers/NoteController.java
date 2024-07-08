package com.example.rebrain.controllers;

import com.example.rebrain.exception.NoteNotFoundException;
import com.example.rebrain.model.UpdateNote;
import lombok.AllArgsConstructor;
import com.example.rebrain.entity.NoteEntity;
import com.example.rebrain.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity createNote(@RequestBody NoteEntity note) {
        try {
            return ResponseEntity.ok(noteService.create(note));
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
    public ResponseEntity updateNote(@PathVariable Integer id, @RequestBody UpdateNote updateNoteDto) {
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
            return ResponseEntity.ok(noteService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
