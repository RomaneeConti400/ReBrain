package com.example.rebrain.services;

import com.example.rebrain.entity.SetEntity;
import com.example.rebrain.entity.SetEntity;
import com.example.rebrain.entity.SetEntity;
import com.example.rebrain.entity.SetEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.SetRepo;
import com.example.rebrain.dto.SetDto;
import com.example.rebrain.util.ThreadLocalUserIdHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SetService {
    @Autowired
    private final SetRepo setRepo;

    public SetEntity create(SetEntity setEntity) {
        Long userId = Long.valueOf(ThreadLocalUserIdHolder.get());
        setEntity.setUserId(userId);
        log.info("Saving new {}", setEntity);
        return setRepo.save(setEntity);
    }

    public List<SetEntity> getAll() {
        return setRepo.findAll();
    }

    public SetEntity getOne(Integer id) throws ObjectNotFoundException {
        return setRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Set with ID " + id + " not found"));
    }

    public SetEntity update(Integer id, SetEntity updateEntity) throws ObjectNotFoundException {
        SetEntity setEntity = getEntityById(id);
        if (updateEntity.getTitle() != null) {
            setEntity.setTitle(updateEntity.getTitle());
        }

        if (updateEntity.getDescription() != null) {
            setEntity.setDescription(updateEntity.getDescription());
        }
        return setRepo.save(setEntity);
    }

    public void delete(Integer id) throws ObjectNotFoundException {
        SetEntity setEntity = getEntityById(id);
        setRepo.delete(setEntity);
    }

    private SetEntity getEntityById(Integer id) throws ObjectNotFoundException {
        Optional<SetEntity> setOptional = setRepo.findById(id);
        return setOptional.orElseThrow(() -> new ObjectNotFoundException("Set with ID " + id + " not found"));
    }
}
