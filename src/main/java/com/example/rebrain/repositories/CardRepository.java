package com.example.rebrain.repositories;

import com.example.rebrain.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByTitle(String title);
}
