package uoc.tfg.cvelascofa.pageturner_backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.UserStatistics;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStatisticsRepository  extends JpaRepository<UserStatistics, Long> {

    Optional<UserStatistics> findByUserId(Long userId);

}