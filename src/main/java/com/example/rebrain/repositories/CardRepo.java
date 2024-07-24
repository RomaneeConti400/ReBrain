package com.example.rebrain.repositories;

import com.example.rebrain.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepo extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findByTitle(String title);
}
