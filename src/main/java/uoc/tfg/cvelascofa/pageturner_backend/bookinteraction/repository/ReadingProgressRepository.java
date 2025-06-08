package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.enums.ReadingStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {

    Optional<ReadingProgress> findByUserIdAndBookId(Long userId, Long bookId);
    void deleteByUserIdAndBookId(Long userId, Long bookId);
    List<ReadingProgress> findAllByUserIdAndBookId(Long userId, Long bookId);
    Page<ReadingProgress> findByUserIdAndBookId(Long userId, Long bookId, Pageable pageable);

    @Query("SELECT rp FROM ReadingProgress rp WHERE rp.user.id = :userId AND rp.readingStatus = :readingStatus")
    List<ReadingProgress> findByUserIdAndReadingStatus(@Param("userId") Long userId, @Param("readingStatus") ReadingStatus readingStatus);

    @Query("SELECT SUM(r.pagesRead) FROM ReadingProgress r " +
            "WHERE r.user.id = :userId AND MONTH(r.progressDate) = :month AND YEAR(r.progressDate) = :year")
    Optional<Integer> sumPagesReadByUserIdAndMonth(@Param("userId") Long userId,
                                                   @Param("month") int month,
                                                   @Param("year") int year);

    @Query("SELECT COUNT(DISTINCT rp.book.id) FROM ReadingProgress rp " +
            "WHERE rp.user.id = :userId " +
            "AND rp.readingStatus = 'COMPLETED' " +
            "AND MONTH(rp.progressDate) = :month " +
            "AND YEAR(rp.progressDate) = :year")
    Optional<Integer> countCompletedBooksByUserIdAndMonth(@Param("userId") Long userId,
                                                          @Param("month") int month,
                                                          @Param("year") int year);

}