package com.example.rebrain.repositories;

import com.example.rebrain.entity.TestStatisticsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestStatisticsRepo extends JpaRepository<TestStatisticsView, Long> {
    TestStatisticsView findByUserIdAndSetId(Long userId, Long setId);
}

