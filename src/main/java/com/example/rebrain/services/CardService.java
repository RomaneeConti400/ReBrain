package com.example.rebrain.services;

import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.CardRepo;
import org.springframework.transaction.annotation.Transactional;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.entity.CardsSetsEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.mapper.CardsSetsMapper;
import com.example.rebrain.repositories.CardRepo;
import com.example.rebrain.util.ThreadLocalUserIdHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    private final CardsSetsRepo cardsSetsRepo;
    private final CardRepo cardRepo;

    @Transactional
    public CardEntity create(CardEntity cardEntity, Long setId) {
        Long userId = Long.valueOf(ThreadLocalUserIdHolder.get());
        cardEntity.setUserId(userId);
        log.debug("Saving new card: {}", cardEntity);
        CardEntity savedEntity = cardRepo.save(cardEntity);
        log.info("Card saved with ID: {}", savedEntity.getId());
        CardsSetsEntity cardsSetsEntity = new CardsSetsEntity(savedEntity.getId(), setId);
        cardsSetsRepo.save(cardsSetsEntity);
        return savedEntity;
    }

    public List<CardEntity> getAll() {
        log.debug("Fetching all cards");
        return cardRepo.findAll();
    }

    public CardEntity getOne(Long id) {
        log.debug("Fetching card with ID: {}", id);
        return cardRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Card with ID: {} not found", id);
                    return new ObjectNotFoundException("Card with ID " + id + " not found");
                });
    }

    public CardEntity update(Long id, CardEntity updateEntity) {
        log.debug("Updating card with ID: {} with data: {}", id, updateEntity);
        CardEntity cardEntity = getEntityById(id);
        if (updateEntity.getTitle() != null) {
            cardEntity.setTitle(updateEntity.getTitle());
        }
        if (updateEntity.getDescription() != null) {
            cardEntity.setDescription(updateEntity.getDescription());
        }
        CardEntity updatedEntity = cardRepo.save(cardEntity);
        log.info("Card with ID: {} updated", id);
        return updatedEntity;
    }

    public void delete(Long id) {
        log.debug("Deleting card with ID: {}", id);
        CardEntity cardEntity = getEntityById(id);
        cardRepo.delete(cardEntity);
        log.info("Card with ID: {} deleted", id);
    }

    private CardEntity getEntityById(Long id) {
        log.debug("Fetching card entity with ID: {}", id);
        return cardRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Card entity with ID: {} not found", id);
                    return new ObjectNotFoundException("Card with ID " + id + " not found");
                });
    }
}