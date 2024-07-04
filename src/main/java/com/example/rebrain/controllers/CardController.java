package com.example.rebrain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardController {
    @GetMapping("/cards")
    public String cards() {
        return "cards";
    }
}
