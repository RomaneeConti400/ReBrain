package com.example.rebrain.repositories;

import com.example.rebrain.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<ScheduleEntity, Long> {
}
