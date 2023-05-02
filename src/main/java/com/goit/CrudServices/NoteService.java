package com.goit.CrudServices;

import Repository.NoteRepository;
import com.goit.Entities.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static com.goit.JavaDevModule14Application.notesStorage;

@Service
@RequiredArgsConstructor
public class NoteService {

    public final NoteRepository repository;

//    public Map<Long, Note> listAll() {
//        return notesStorage;
//    }

    public Map<Long, Note> listAll() {
        return repository.findAll().stream().collect(Collectors.toMap(Note::getId, note -> note));
    }

    public Note add(Note note) {
        Random random = new Random();
        //note.setId(random.nextLong(1L, Long.MAX_VALUE));
        note.setId(random.nextLong(1L, 1000L));
        notesStorage.put(note.getId(), note);
        return note;
    }

//    public void deleteById(long id) {
//        if (notesStorage.get(id) == null) {
//            throw new IllegalArgumentException("There is not any note to DELETE with such ID");
//        }
//        notesStorage.remove(id);
//    }

    public void deleteById(long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("There is not any note to DELETE with such ID");
        }
        repository.deleteById(id);
    }

    public void update(Note note) {
        if (notesStorage.get(note.getId()) == null) {
            throw new IllegalArgumentException("There is not any note to UPDATE with such ID");
        }
        notesStorage.replace(note.getId(), note);
    }

    public Note getById(long id) {
        if (notesStorage.get(id) == null) {
            throw new IllegalArgumentException("There is not any note to GET with such ID");
        }
        return notesStorage.get(id);
    }

}
