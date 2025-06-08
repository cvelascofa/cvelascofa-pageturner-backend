package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.UserStatistics;

import java.util.Optional;

@Repository
public interface UserStatisticsRepository  extends JpaRepository<UserStatistics, Long> {

    Optional<UserStatistics> findByUserId(Long userId);

}