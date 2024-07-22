package com.example.rebrain.controllers;

import com.example.rebrain.dto.CardDto;
import com.example.rebrain.dto.CardsSetsGetDto;
import com.example.rebrain.dto.SetDto;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.entity.SetEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.mapper.CardMapper;
import com.example.rebrain.mapper.SetMapper;
import lombok.AllArgsConstructor;
import com.example.rebrain.services.SetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sets")
@AllArgsConstructor
public class SetController {

    private SetService setService;

    @PostMapping
    public ResponseEntity<SetDto> createSet(@RequestBody SetDto setDto) {
        SetEntity setEntity = SetMapper.toEntity(setDto);
        SetEntity createdSet = setService.create(setEntity);
        SetDto createdToDto = SetMapper.toDto(createdSet);
        URI location = URI.create("/sets/" + createdToDto.getId());
        return ResponseEntity.created(location).body(createdToDto);
    }

    @GetMapping
    public ResponseEntity<List<SetDto>> getAllSets() {
        try {
            List<SetEntity> setsEntities = setService.getAll();
            List<SetDto> setDtos = setsEntities.stream()
                    .map(SetMapper::toDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(setDtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneSet(@PathVariable Integer id) {
        try {
            SetDto set = SetMapper.toDto(setService.getOne(id));
            return ResponseEntity.ok(set);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<CardsSetsGetDto> getCardsBySetId(@PathVariable Integer id) {
        List<CardEntity> cardEntities = setService.getCardsBySetId(id);
        List<CardDto> cardDtos = cardEntities.stream()
                .map(CardMapper::toDto)
                .collect(Collectors.toList());
        SetEntity setEntity = setService.getOne(id);
        SetDto setDto = SetMapper.toDto(setEntity);
        return ResponseEntity.ok(new CardsSetsGetDto(id, setDto.getTitle(), setDto.getDescription(), cardDtos));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SetDto> updateSet(@PathVariable Integer id, @RequestBody SetDto setDto) {
        try {
            SetEntity updateEntity = SetMapper.toEntity(setDto);
            SetDto updatedSet = SetMapper.toDto(setService.update(id, updateEntity));
            return ResponseEntity.ok(updatedSet);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSet(@PathVariable Integer id) {
        try {
            setService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
