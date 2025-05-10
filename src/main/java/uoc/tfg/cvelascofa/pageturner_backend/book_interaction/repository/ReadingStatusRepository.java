package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingStatus;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.enums.ReadingStatusCode;

import java.util.Optional;

@Repository
public interface ReadingStatusRepository extends JpaRepository<ReadingStatus, Long> {
    Optional<ReadingStatus> findByCode(ReadingStatusCode code);
}