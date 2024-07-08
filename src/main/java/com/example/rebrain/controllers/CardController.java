package com.example.rebrain.controllers;

import com.example.rebrain.exception.CardNotFoundException;
import com.example.rebrain.Dto.UpdateCardDto;
import lombok.AllArgsConstructor;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity createCard(@RequestBody CardEntity card) {
        try {
            CardEntity createdCard = cardService.create(card);
            URI location = URI.create("/cards/" + createdCard.getId());
            return ResponseEntity.created(location).body(createdCard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(cardService.getAll());
        } catch (Exception e) {
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
    public ResponseEntity updateCard(@PathVariable Integer id, @RequestBody UpdateCardDto updateCardDto) {
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
            cardService.delete(id);
            return ResponseEntity.noContent().build();  // Возвращает код 204
        } catch (CardNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  // Возвращает код 404
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
