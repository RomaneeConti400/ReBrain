package com.example.rebrain.controllers;

import com.example.rebrain.dto.CardDto;
import com.example.rebrain.dto.CardsSetsDto;
import com.example.rebrain.dto.CardsSetsGetDto;
import com.example.rebrain.dto.SetDto;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.entity.CardsSetsEntity;
import com.example.rebrain.entity.SetEntity;
import com.example.rebrain.mapper.CardMapper;
import com.example.rebrain.mapper.CardsSetsMapper;
import com.example.rebrain.mapper.SetMapper;
import com.example.rebrain.services.CardsSetsService;
import com.example.rebrain.services.SetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/cardsSets")
public class CardsSetsController {

    private final CardsSetsService cardsSetsService;
    private final SetService setService;
    public CardsSetsController(CardsSetsService cardsSetsService, SetService setService) {
        this.cardsSetsService = cardsSetsService;
        this.setService = setService;
    }

    @PostMapping
    public ResponseEntity<CardsSetsDto> createCardsSets(@RequestBody CardsSetsDto cardsSetsDto) {
        log.debug("Creating cardsSets with data: {}", cardsSetsDto);
        CardsSetsEntity cardsSetsEntity = CardsSetsMapper.toEntity(cardsSetsDto);
        CardsSetsEntity createdCardsSets = cardsSetsService.create(cardsSetsEntity);
        CardsSetsDto createdToDto = CardsSetsMapper.toDto(createdCardsSets);
        URI location = URI.create("/cardsSets/" + createdToDto.getSetId());
        return ResponseEntity.created(location).body(createdToDto);
    }

    @GetMapping("/{setId}")
    public ResponseEntity<CardsSetsGetDto> getCardsBySetId(@PathVariable Integer setId) {
        List<CardEntity> cardEntities = cardsSetsService.getCardsBySetId(setId);
        List<CardDto> cardDtos = cardEntities.stream()
                .map(CardMapper::toDto)
                .collect(Collectors.toList());
        SetEntity setEntity = setService.getOne(setId);
        SetDto setDto = SetMapper.toDto(setEntity);
        return ResponseEntity.ok(new CardsSetsGetDto(setId, setDto.getTitle(), setDto.getDescription(), cardDtos));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CardsSetsDto> updateCardsSets(@PathVariable Integer id, @RequestBody CardsSetsDto cardsSetsDto) {
        log.debug("Updating cardsSets with ID: {} with data: {}", id, cardsSetsDto);
        CardsSetsEntity updateEntity = CardsSetsMapper.toEntity(cardsSetsDto);
        CardsSetsDto updatedCardsSets = CardsSetsMapper.toDto(cardsSetsService.update(id, updateEntity));
        log.info("CardsSets with ID: {} updated", id);
        return ResponseEntity.ok(updatedCardsSets);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardsSets(@PathVariable Integer id) {
        log.info("Deleting cardsSets with ID: {}", id);
        cardsSetsService.delete(id);
        log.info("CardsSets with ID: {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
