package com.example.rebrain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoteController {
    @GetMapping("/notes")
    public String notes() {
        return "notes";
    }
}
