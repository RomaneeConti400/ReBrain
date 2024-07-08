package com.example.rebrain.services;

import com.example.rebrain.exception.SetNotFoundException;
import com.example.rebrain.entity.SetEntity;
import com.example.rebrain.model.UpdateSet;
import com.example.rebrain.repositories.SetRepo;
import com.example.rebrain.model.Set;
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

    public SetEntity create(SetEntity set) {
        log.info("Saving new {}", set);
        return setRepo.save(set);
    }

    public List<SetEntity> getAll() {
        return setRepo.findAll();
    }

    public SetEntity getOne(Integer id) throws SetNotFoundException {
        Optional<SetEntity> set = setRepo.findById(id);
        if (set.isPresent()) {
            return set.get();
        } else {
            throw new SetNotFoundException("Set with ID " + id + " not found");
        }
    }

    public Set update(Integer id, UpdateSet updateSet) throws SetNotFoundException {
        SetEntity set = getOne(id);

        if (updateSet.getTitle() != null) {
            set.setTitle(updateSet.getTitle());
        }

        if (updateSet.getDescr() != null) {
            set.setDescr(updateSet.getDescr());
        }

        SetEntity savedSet = setRepo.save(set);
        return Set.toModel(savedSet);
    }

    public Integer delete(Integer id) throws SetNotFoundException {
        setRepo.deleteById(id);
        return id;
    }
}
