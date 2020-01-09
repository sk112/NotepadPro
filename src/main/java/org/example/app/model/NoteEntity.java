package org.example.app.model;

import lombok.Data;

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

    public NoteEntity(String domain, String note){
        this.domain = domain;
        this.note = note;
    }

    public NoteEntity(){}
}
