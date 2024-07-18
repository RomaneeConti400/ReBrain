package com.example.rebrain.repositories;

import com.example.rebrain.entity.CardsSetsEntity;
import com.example.rebrain.entity.CardsSetsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardsSetsRepo extends JpaRepository<CardsSetsEntity, CardsSetsId> {
    List<CardsSetsEntity> findBySetId(Integer setId);
}