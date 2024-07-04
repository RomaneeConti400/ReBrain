package com.example.rebrain.controllers;


import com.example.rebrain.models.Card;
import com.example.rebrain.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/")
    public String cards(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("cards", cardService.listCards(title));
        return "cards";
    }

    @GetMapping("/card/{id}")
    public String cardInfo(@PathVariable Integer id, Model model) {
        model.addAttribute("card", cardService.getCardById(id));
        return "card-info";
    }

    @PostMapping("/card/create")
    public String createCard(Card card) {
        cardService.saveCard(card);
        return "redirect:/";
    }

    @PostMapping("/card/delete/{id}")
    public String deleteCard(@PathVariable Integer id) {
        cardService.deleteCard(id);
        return "redirect:/";
    }
}
