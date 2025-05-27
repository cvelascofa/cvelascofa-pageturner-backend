package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByUserIdAndBookId(Long userId, Long bookId);
    void deleteByUserIdAndBookId(Long userId, Long bookId);
    List<Review> findByBookId(Long bookId);
    List<Review> findByUserId(Long userId);

}