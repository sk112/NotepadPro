package org.example.app.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Data
@Table(name="note_table")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String domain;

    private String note;

    private Integer trigger=0;

    public NoteEntity(String domain, String note, Integer trigger){
        this.domain = domain;
        this.note = note;
        this.trigger = trigger;
    }

    public NoteEntity(){}
}
