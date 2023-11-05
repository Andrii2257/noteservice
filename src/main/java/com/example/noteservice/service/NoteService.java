package com.example.noteservice.service;

import com.example.noteservice.model.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private Map<Long, Note> notes = new LinkedHashMap<>();
    private Random random = new Random();
    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }
    public Note add(Note note) {
        Long id = getUniqueId();
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    public void deleteById(Long id) {
        if (notes.containsKey(id)) {
            notes.remove(id);
        } else {
            throw new NoSuchElementException("Note with id = " + id + " not found!");
        }
    }

    public void update(Note note) {
        if (notes.containsKey(note.getId())) {
            notes.put(note.getId(), note);
        } else {
            throw new NoSuchElementException("Note with id = " + note.getId() + " not found!");
        }
    }

    public Note getById(long id) {
        if (notes.containsKey(id)) {
            return notes.get(id);
        } else {
            throw new NoSuchElementException("Note with id = " + id + " not found!");
        }
    }

    private Long getUniqueId() {
        Long id;
        do {
            id = random.nextLong();
        } while (notes.containsKey(id));
        return id;
    }
}
