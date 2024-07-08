package com.example.rebrain.services;

import com.example.rebrain.exception.CardNotFoundException;
import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.Dto.UpdateCardDto;
import com.example.rebrain.repositories.CardRepo;
import com.example.rebrain.Dto.CardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {
    @Autowired
    private final CardRepo cardRepo;

    public CardEntity create(CardEntity card) {
        log.info("Saving new {}", card);
        return cardRepo.save(card);
    }

    public List<CardEntity> getAll() {
        return cardRepo.findAll();
    }

    public CardEntity getOne(Integer id) throws CardNotFoundException {
        Optional<CardEntity> card = cardRepo.findById(id);
        if (card.isPresent()) {
            return card.get();
        } else {
            throw new CardNotFoundException("Card with ID " + id + " not found");
        }
    }

    public CardDto update(Integer id, UpdateCardDto updateCard) throws CardNotFoundException {
        CardEntity card = getOne(id);

        if (updateCard.getTitle() != null) {
            card.setTitle(updateCard.getTitle());
        }

        if (updateCard.getDescr() != null) {
            card.setDescr(updateCard.getDescr());
        }

        CardEntity savedCard = cardRepo.save(card);
        return CardDto.toModel(savedCard);
    }

    public Integer delete(Integer id) throws CardNotFoundException {
        cardRepo.deleteById(id);
        return id;
    }

}