package com.example.rebrain.repositories;

import com.example.rebrain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
}
