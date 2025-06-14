package uoc.tfg.cvelascofa.pageturner_backend.gamification.service.interfaces;

import org.springframework.data.domain.Page;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.UserChallengeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.UserChallengeWithBadgeDTO;

import java.util.List;

public interface UserChallengeService {

    UserChallengeDTO createUserChallenge(UserChallengeDTO dto);
    List<UserChallengeDTO> getUserChallenges(Long userId);
    Page<UserChallengeWithBadgeDTO> getUserChallengesWithBadges(Long userId, int page, int size, String sortDirection);
    boolean hasUserCompletedChallenge(Long userId, Long challengeId);

}