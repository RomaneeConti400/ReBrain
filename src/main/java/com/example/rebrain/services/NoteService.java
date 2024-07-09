package com.example.rebrain.services;

import com.example.rebrain.dto.NoteDto;
import com.example.rebrain.dto.UpdateNoteDto;
import com.example.rebrain.entity.NoteEntity;
import com.example.rebrain.exception.ObjectNotFoundException;
import com.example.rebrain.repositories.NoteRepo;
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
        log.info("Saving new {}", noteEntity);
        return noteRepo.save(noteEntity);
    }

    public List<NoteEntity> getAll() {
        return noteRepo.findAll();
    }

    public NoteEntity getOne(Integer id) throws ObjectNotFoundException {
        Optional<NoteEntity> noteOptional = noteRepo.findById(id);
        return noteOptional.orElseThrow(() -> new ObjectNotFoundException("Note with ID " + id + " not found"));
    }

    public NoteEntity update(Integer id, NoteEntity updateEntity) throws ObjectNotFoundException {
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

    public void delete(Integer id) throws ObjectNotFoundException {
        NoteEntity noteEntity = getEntityById(id);
        noteRepo.delete(noteEntity);
    }

    private NoteEntity getEntityById(Integer id) throws ObjectNotFoundException {
        Optional<NoteEntity> noteOptional = noteRepo.findById(id);
        return noteOptional.orElseThrow(() -> new ObjectNotFoundException("Note with ID " + id + " not found"));
    }
}
