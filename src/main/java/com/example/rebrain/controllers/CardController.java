package com.example.rebrain.controllers;

import com.example.rebrain.dto.CardDto;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.mapper.CardMapper;
import com.example.rebrain.services.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody CardDto cardDto) {
        CardEntity cardEntity = CardMapper.toEntity(cardDto);
        CardEntity createdCard = cardService.create(cardEntity);
        CardDto createdToDto = CardMapper.toDto(createdCard);
        URI location = URI.create("/cards/" + createdToDto.getId());
        return ResponseEntity.created(location).body(createdToDto);
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards() {
        List<CardEntity> cardsEntities = cardService.getAll();
        List<CardDto> cardDtos = cardsEntities.stream()
                .map(CardMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cardDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable Integer id) {
        CardDto card = CardMapper.toDto(cardService.getOne(id));
        return ResponseEntity.ok(card);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CardDto> updateCard(@PathVariable Integer id, @RequestBody CardDto cardDto) {
        CardEntity updateEntity = CardMapper.toEntity(cardDto);
        CardDto updatedCard = CardMapper.toDto(cardService.update(id, updateEntity));
        return ResponseEntity.ok(updatedCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Integer id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
