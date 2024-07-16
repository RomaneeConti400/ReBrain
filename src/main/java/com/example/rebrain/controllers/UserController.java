package com.example.rebrain.controllers;

import com.example.rebrain.dto.UserDto;
import com.example.rebrain.entity.UserEntity;
import com.example.rebrain.mapper.UserMapper;
import com.example.rebrain.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.debug("Creating user with data: {}", userDto);
        UserEntity userEntity = UserMapper.toEntity(userDto);
        UserEntity createdUser = userService.create(userEntity);
        UserDto createdToDto = UserMapper.toDto(createdUser);
        URI location = URI.create("/users/" + createdToDto.getId());
        log.info("User created with ID: {}", createdToDto.getId());
        return ResponseEntity.created(location).body(createdToDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.debug("Fetching all users");
        List<UserEntity> usersEntities = userService.getAll();
        List<UserDto> userDtos = usersEntities.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
        log.debug("Returning {} users", userDtos.size());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        log.debug("Fetching user with ID: {}", id);
        UserDto user = UserMapper.toDto(userService.getOne(id));
        log.debug("User found: {}", user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        log.debug("Updating user with ID: {} with data: {}", id, userDto);
        UserEntity updateEntity = UserMapper.toEntity(userDto);
        UserDto updatedUser = UserMapper.toDto(userService.update(id, updateEntity));
        log.info("User with ID: {} updated", id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        log.info("Deleting user with ID: {}", id);
        userService.delete(id);
        log.info("User with ID: {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
