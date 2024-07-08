package com.example.rebrain.services;

import com.example.rebrain.exception.NoteNotFoundException;
import com.example.rebrain.entity.NoteEntity;
import com.example.rebrain.model.UpdateNote;
import com.example.rebrain.repositories.NoteRepo;
import com.example.rebrain.model.Note;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoteService {
    @Autowired
    private final NoteRepo noteRepo;

    public NoteEntity create(NoteEntity note) {
        log.info("Saving new {}", note);
        return noteRepo.save(note);
    }

    public List<NoteEntity> getAll() {
        return noteRepo.findAll();
    }

    public NoteEntity getOne(Integer id) throws NoteNotFoundException {
        Optional<NoteEntity> note = noteRepo.findById(id);
        if (note.isPresent()) {
            return note.get();
        } else {
            throw new NoteNotFoundException("Note with ID " + id + " not found");
        }
    }

    public Note update(Integer id, UpdateNote updateNote) throws NoteNotFoundException {
        NoteEntity note = getOne(id);

        if (updateNote.getTitle() != null) {
            note.setTitle(updateNote.getTitle());
        }

        if (updateNote.getText() != null) {
            note.setText(updateNote.getText());
        }

        if (updateNote.getDescr() != null) {
            note.setDescr(updateNote.getDescr());
        }

        NoteEntity savedNote = noteRepo.save(note);
        return Note.toModel(savedNote);
    }

    public Integer delete(Integer id) throws NoteNotFoundException {
        noteRepo.deleteById(id);
        return id;
    }
}
