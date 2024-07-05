package com.example.rebrain.controllersREST;

import lombok.AllArgsConstructor;
import com.example.rebrain.dto.CardDTO;
import com.example.rebrain.models.Card;
import com.example.rebrain.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {

    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody CardDTO dto) {
        return mappingResponseCard(cardService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<Card>> readAll() {
        return mappingResponseListCard(cardService.readAll());
    }

    @PutMapping
    public ResponseEntity<Card> update(@RequestBody Card card) {
        return mappingResponseCard(cardService.update(card));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        cardService.delete(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<Card> mappingResponseCard(Card card) {
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    private ResponseEntity<List<Card>> mappingResponseListCard(List<Card> cards) {
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}
