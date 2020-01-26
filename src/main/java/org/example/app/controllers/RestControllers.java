package org.example.app.controllers;

import org.example.app.model.NoteEntity;
import org.example.app.repository.NoteRepository;
import org.example.app.utils.DomainUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RestControllers {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("rest/trigger/{param}")
    public Integer getTriggerOfCurrentDomain(@PathVariable("param") Integer Id){
        Optional<NoteEntity> triggerNoteEntity = this.noteRepository.findById(Id);

        if(triggerNoteEntity.isPresent()){
            return triggerNoteEntity.get().getTrigger();
        }
        return 0;
    }

    @GetMapping("rest/getnote/{param}")
    public String getNoteFromCurrentDomain(@PathVariable("param") Integer Id){
        Optional<NoteEntity> triggerNoteEntity = this.noteRepository.findById(Id);

        if(triggerNoteEntity.isPresent()){
            return triggerNoteEntity.get().getNote();
        }

        return "";
    }
}
