package org.example.app.repository;

import org.example.app.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Integer>, JpaSpecificationExecutor<NoteEntity> {
    NoteEntity findByDomain(String domain);
}
