package com.example.rebrain.services;

import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.CardRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    private final CardRepo cardRepo;

    public CardEntity create(CardEntity cardEntity) {
        log.info("Saving new {}", cardEntity);
        return cardRepo.save(cardEntity);
    }

    public List<CardEntity> getAll() {
        return cardRepo.findAll();
    }

    public CardEntity getOne(Integer id) {
        return cardRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Card with ID " + id + " not found"));
    }

    public CardEntity update(Integer id, CardEntity updateEntity) {
        CardEntity cardEntity = getEntityById(id);
        if (updateEntity.getTitle() != null) {
            cardEntity.setTitle(updateEntity.getTitle());
        }
        if (updateEntity.getDescription() != null) {
            cardEntity.setDescription(updateEntity.getDescription());
        }
        return cardRepo.save(cardEntity);
    }

    public void delete(Integer id) {
        CardEntity cardEntity = getEntityById(id);
        cardRepo.delete(cardEntity);
    }

    private CardEntity getEntityById(Integer id) {
        return cardRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Card with ID " + id + " not found"));
    }
}
