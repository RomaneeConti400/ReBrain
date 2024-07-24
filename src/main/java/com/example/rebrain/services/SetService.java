package com.example.rebrain.services;

import com.example.rebrain.entity.SetEntity;
import com.example.rebrain.entity.*;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.dto.UpdateSetDto;
import com.example.rebrain.repositories.CardRepo;
import com.example.rebrain.repositories.CardsSetsRepo;
import com.example.rebrain.repositories.SetRepo;
import com.example.rebrain.dto.SetDto;
import com.example.rebrain.util.ThreadLocalUserIdHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SetService {

    private final CardsSetsRepo cardsSetsRepo;
    private final SetRepo setRepo;
    private final CardRepo cardRepo;

    public SetEntity create(SetEntity setEntity) {
        Long userId = Long.valueOf(ThreadLocalUserIdHolder.get());
        setEntity.setUserId(userId);
        log.info("Saving new {}", setEntity);
        return setRepo.save(setEntity);
    }

    public List<SetEntity> getAll() {
        return setRepo.findAll();
    }

    public SetEntity getOne(Long id) throws ObjectNotFoundException {
        return setRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Set with ID " + id + " not found"));
    }

    public List<CardEntity> getCardsBySetId(Long setId) {
        List<CardsSetsEntity> cardsSetsEntities = cardsSetsRepo.findBySetId(setId);
        List<Long> cardIds = cardsSetsEntities.stream()
                .map(CardsSetsEntity::getCardId)
                .collect(Collectors.toList());
        return cardRepo.findAllById(cardIds);
    }

    public SetEntity update(Long id, SetEntity updateEntity) throws ObjectNotFoundException {
        SetEntity setEntity = getEntityById(id);
        if (updateEntity.getTitle() != null) {
            setEntity.setTitle(updateEntity.getTitle());
        }

        if (updateEntity.getDescription() != null) {
            setEntity.setDescription(updateEntity.getDescription());
        }
        return setRepo.save(setEntity);
    }

    public void delete(Long id) throws ObjectNotFoundException {
        SetEntity setEntity = getEntityById(id);
        setRepo.delete(setEntity);
    }

    private SetEntity getEntityById(Long id) throws ObjectNotFoundException {
        Optional<SetEntity> setOptional = setRepo.findById(id);
        return setOptional.orElseThrow(() -> new ObjectNotFoundException("Set with ID " + id + " not found"));
    }
}
