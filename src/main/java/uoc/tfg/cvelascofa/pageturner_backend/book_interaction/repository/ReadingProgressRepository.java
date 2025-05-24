package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingProgress;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {

    Optional<ReadingProgress> findByUserIdAndBookId(Long userId, Long bookId);
    void deleteByUserIdAndBookId(Long userId, Long bookId);
    List<ReadingProgress> findAllByUserIdAndBookId(Long userId, Long bookId);
    Page<ReadingProgress> findByUserIdAndBookId(Long userId, Long bookId, Pageable pageable);

}