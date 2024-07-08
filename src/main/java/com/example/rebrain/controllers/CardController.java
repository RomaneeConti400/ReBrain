package com.example.rebrain.controllers;

import com.example.rebrain.exception.CardNotFoundException;
import com.example.rebrain.model.UpdateCard;
import lombok.AllArgsConstructor;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {
    
    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity createCard(@RequestBody CardEntity card) {
        try {
            return ResponseEntity.ok(cardService.create(card));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(cardService.getAll());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneCard(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(cardService.getOne(id));
        } catch (CardNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateCard(@PathVariable Integer id, @RequestBody UpdateCard updateCardDto) {
        try {
            return ResponseEntity.ok(cardService.update(id, updateCardDto));
        } catch (CardNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCard(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(cardService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
