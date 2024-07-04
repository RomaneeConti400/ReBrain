package com.example.rebrain.services;

import com.example.rebrain.models.Card;
import com.example.rebrain.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public List<Card> listCards(String title) {
        if (title != null) return cardRepository.findByTitle(title);
        return cardRepository.findAll();
    }

    public void saveCard(Card card) {
        log.info("Saving new {}", card);
        cardRepository.save(card);
    }

    public void deleteCard(Integer id) {
        cardRepository.deleteById(id);
    }

    public Card getCardById(Integer id) {
        return cardRepository.findById(id).orElse(null);
    }
}