package com.goit;

import com.goit.CrudServices.NoteService;
import com.goit.Entities.Note;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories("Repository")
public class JavaDevModule14Application {

    public static final Map<Long, Note> notesStorage = new HashMap<>();

    public static void main(String[] args) {

        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:file:./src/main/resources/db/module14db", "sa", null).load();
        flyway.migrate();

//        NoteService noteService = new NoteService();
//        for (int i = 1; i <= 5; i++) {
//            Note note = new Note();
//            note.setTitle("Title " + i);
//            note.setContent("My " + i + " note");
//            noteService.add(note);
//        }

        SpringApplication.run(JavaDevModule14Application.class, args);
    }

}
