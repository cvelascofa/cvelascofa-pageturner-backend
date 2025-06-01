package uoc.tfg.cvelascofa.pageturner_backend.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.entity.UserChallenge;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.entity.UserChallengeId;

import java.util.List;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, UserChallengeId> {

    boolean existsById(UserChallengeId id);
    List<UserChallenge> findByUserId(Long userId);
    boolean existsByUserIdAndChallengeId(Long userId, Long challengeId);
    Page<UserChallenge> findByUserId(Long userId, Pageable pageable);
    @Query("SELECT uc FROM UserChallenge uc JOIN FETCH uc.challenge WHERE uc.user.id = :userId")
    List<UserChallenge> findByUserIdWithChallenge(@Param("userId") Long userId);

}