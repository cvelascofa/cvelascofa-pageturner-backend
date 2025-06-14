package uoc.tfg.cvelascofa.pageturner_backend.gamification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.Challenge;

import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Optional<Challenge> findByName(String name);
}