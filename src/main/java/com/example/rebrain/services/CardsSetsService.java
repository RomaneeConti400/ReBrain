package com.example.rebrain.services;

import com.example.rebrain.dto.CardDto;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.entity.CardsSetsEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.CardRepo;
import com.example.rebrain.repositories.CardsSetsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardsSetsService {

    private final CardsSetsRepo cardsSetsRepo;
    private final CardRepo cardRepo;

    public CardsSetsEntity create(CardsSetsEntity cardsSetsEntity) {
        log.debug("Saving new cardsSets: {}", cardsSetsEntity);
        CardsSetsEntity savedEntity = cardsSetsRepo.save(cardsSetsEntity);
        log.info("CardsSets saved.");
        return savedEntity;
    }

    public List<CardEntity> getCardsBySetId(Integer setId) {
        List<CardsSetsEntity> cardsSetsEntities = cardsSetsRepo.findBySetId(setId);
        List<Integer> cardIds = cardsSetsEntities.stream()
                .map(CardsSetsEntity::getCardId)
                .collect(Collectors.toList());

        return cardRepo.findAllById(cardIds);
    }

//    public CardsSetsEntity getOne(Integer id) {
//        log.debug("Fetching cardsSets ", id);
//        return cardsSetsRepo.findById(id);
//    }

    public CardsSetsEntity update(Integer id, CardsSetsEntity updateEntity) {
//        log.debug("Updating cardsSets  with data: {}", id, updateEntity);
//        CardsSetsEntity cardsSetsEntity = getEntityById(id);
//        if (updateEntity.getCardId() != null) {
//            cardsSetsEntity.setCardId(updateEntity.getCardId());
//        }
//        if (updateEntity.getSetId() != null) {
//            cardsSetsEntity.setSetId(updateEntity.getSetId());
//        }
//        CardsSetsEntity updatedEntity = cardsSetsRepo.save(cardsSetsEntity);
//        log.info("CardsSets updated");
          return updateEntity;
    }

    public void delete(Integer id) {
        //сделать удаление по составному ключу
    }
}