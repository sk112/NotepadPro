package org.example.app.repository;

import org.example.app.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Integer>, JpaSpecificationExecutor<NoteEntity> {
    NoteEntity findByDomain(String domain);
    Optional<NoteEntity> findById(Integer Id);
//    @Modifying(flushAutomatically = true)
//    @Query("update note_entity as ne set ne.note = :note where ne.domain = :domain")
//    void updateNoteInDatabase(@PathVariable("note") String note, @PathVariable String domain);

}
