package com.example.rebrain.services;

import com.example.rebrain.entity.UserEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserEntity create(UserEntity userEntity) {
        log.debug("Saving new user: {}", userEntity);
        UserEntity savedEntity = userRepo.save(userEntity);
        log.info("User saved with ID: {}", savedEntity.getId());
        return savedEntity;
    }

    public List<UserEntity> getAll() {
        log.debug("Fetching all users");
        return userRepo.findAll();
    }

    public UserEntity getOne(Integer id) {
        log.debug("Fetching user with ID: {}", id);
        return userRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("User with ID: {} not found", id);
                    return new ObjectNotFoundException("User with ID " + id + " not found");
                });
    }

    public UserEntity update(Integer id, UserEntity updateEntity) {
        log.debug("Updating user with ID: {} with data: {}", id, updateEntity);
        UserEntity userEntity = getEntityById(id);
        if (updateEntity.getFirstName() != null) {
            userEntity.setFirstName(updateEntity.getFirstName());
        }
        if (updateEntity.getMiddleName() != null) {
            userEntity.setMiddleName(updateEntity.getMiddleName());
        }
        if (updateEntity.getLastName() != null) {
            userEntity.setLastName(updateEntity.getLastName());
        }
        if (updateEntity.getEmail() != null) {
            userEntity.setEmail(updateEntity.getEmail());
        }
        UserEntity updatedEntity = userRepo.save(userEntity);
        log.info("User with ID: {} updated", id);
        return updatedEntity;
    }

    public void delete(Integer id) {
        log.debug("Deleting user with ID: {}", id);
        UserEntity userEntity = getEntityById(id);
        userRepo.delete(userEntity);
        log.info("User with ID: {} deleted", id);
    }

    private UserEntity getEntityById(Integer id) {
        log.debug("Fetching user entity with ID: {}", id);
        return userRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("User entity with ID: {} not found", id);
                    return new ObjectNotFoundException("User with ID " + id + " not found");
                });
    }
}