package com.example.rebrain.services;

import com.example.rebrain.dto.NoteDto;
import com.example.rebrain.entity.NoteEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.NoteRepo;
import com.example.rebrain.util.ThreadLocalUserIdHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepo noteRepo;

    public NoteEntity create(NoteEntity noteEntity) {
        Long userId = Long.valueOf(ThreadLocalUserIdHolder.get());
        noteEntity.setUserId(userId);
        log.info("Saving new {}", noteEntity);
        return noteRepo.save(noteEntity);
    }

    public List<NoteEntity> getAll() {
        return noteRepo.findAll();
    }

    public NoteEntity getOne(Long id) throws ObjectNotFoundException {
        return noteRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Note with ID " + id + " not found"));
    }

    public NoteEntity update(Long id, NoteEntity updateEntity) throws ObjectNotFoundException {
        NoteEntity noteEntity = getEntityById(id);
        if (updateEntity.getTitle() != null) {
            noteEntity.setTitle(updateEntity.getTitle());
        }
        if (updateEntity.getText() != null) {
            noteEntity.setText(updateEntity.getText());
        }
        if (updateEntity.getDescription() != null) {
            noteEntity.setDescription(updateEntity.getDescription());
        }

        return noteRepo.save(noteEntity);
    }

    public void delete(Long id) throws ObjectNotFoundException {
        NoteEntity noteEntity = getEntityById(id);
        noteRepo.delete(noteEntity);
    }

    private NoteEntity getEntityById(Long id) throws ObjectNotFoundException {
        Optional<NoteEntity> noteOptional = noteRepo.findById(id);
        return noteOptional.orElseThrow(() -> new ObjectNotFoundException("Note with ID " + id + " not found"));
    }
}
