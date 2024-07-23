package com.example.rebrain.repositories;

import com.example.rebrain.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<TestEntity, Integer> {
}