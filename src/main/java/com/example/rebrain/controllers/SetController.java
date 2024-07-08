package com.example.rebrain.controllers;

import com.example.rebrain.exception.SetNotFoundException;
import com.example.rebrain.model.UpdateSet;
import lombok.AllArgsConstructor;
import com.example.rebrain.entity.SetEntity;
import com.example.rebrain.services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sets")
@AllArgsConstructor
public class SetController {

    @Autowired
    private SetService setService;

    @PostMapping
    public ResponseEntity createSet(@RequestBody SetEntity set) {
        try {
            SetEntity createdSet = setService.create(set);
            URI location = URI.create("/sets/" + createdSet.getId());
            return ResponseEntity.created(location).body(createdSet);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(setService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneSet(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(setService.getOne(id));
        } catch (SetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateSet(@PathVariable Integer id, @RequestBody UpdateSet updateSetDto) {
        try {
            return ResponseEntity.ok(setService.update(id, updateSetDto));
        } catch (SetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSet(@PathVariable Integer id) {
        try {
            setService.delete(id);
            return ResponseEntity.noContent().build();  // Возвращает код 204
        } catch (SetNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  // Возвращает код 404
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

}
