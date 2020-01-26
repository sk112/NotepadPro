package org.example.app.utils;

import org.example.app.repository.NoteRepository;
import org.example.app.model.NoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBUtils {

    private  static NoteRepository noteRepository;

    @Autowired
    private void setNoteRepository(NoteRepository noteRepository){
        DBUtils.noteRepository = noteRepository;
    }

    /**
     * Update Domain in DB.
     * @param randomDomain
     */
    static void updateDomainInDB(String randomDomain) {
        updateDomainInDB(randomDomain, "");
    }

    static void updateDomainInDB(String randomDomain, String note){
        NoteEntity noteEntity = new NoteEntity(randomDomain, note, 0);
        noteRepository.save(noteEntity);
    }

    static void findAndUpdateNote(String domain,String note){
        NoteEntity noteEntity = getNoteEntityByDomain(domain);

        noteEntity.setNote(note);
        noteRepository.save(noteEntity);
    }


    static NoteEntity getNoteEntityByDomain(String domain){
        return noteRepository.findByDomain(domain);
    }

    static void saveNoteInDomain(String domain, String note){
        NoteEntity noteEntity = getNoteEntityByDomain(domain);
        noteEntity.setNote(note);
        noteEntity.setTrigger(noteEntity.getTrigger()+1);
        noteRepository.save(noteEntity);
    }

    static Integer getTriggerValue(String domain){
        NoteEntity noteEntity = getNoteEntityByDomain(domain);
        return noteEntity.getTrigger();
    }

}
