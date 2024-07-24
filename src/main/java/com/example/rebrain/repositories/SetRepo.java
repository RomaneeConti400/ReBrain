package com.example.rebrain.repositories;

import com.example.rebrain.entity.CardEntity;
import com.example.rebrain.entity.SetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetRepo extends JpaRepository<SetEntity, Long> {

}
