package com.example.rebrain.repositories;

import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepo extends JpaRepository<NoteEntity, Integer> {
    List<NoteEntity> findByTitle(String title);
}
