package uoc.tfg.cvelascofa.pageturner_backend.gamification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.ChallengeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.Challenge;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.UserChallenge;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.UserChallengeId;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.mapper.ChallengeMapper;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.repository.ChallengeRepository;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.repository.UserChallengeRepository;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.service.interfaces.ChallengeService;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeMapper challengeMapper;
    private final UserChallengeRepository userChallengeRepository;
    private final UserRepository userRepository;

    @Override
    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = challengeMapper.toEntity(challengeDTO);
        Challenge saved = challengeRepository.save(challenge);
        return challengeMapper.toDto(saved);
    }

    @Override
    public List<ChallengeDTO> getAllChallenges() {
        return challengeMapper.toDtoList(challengeRepository.findAll());
    }

    @Override
    public ChallengeDTO getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .map(challengeMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + id));
    }

    @Override
    public void checkAndAwardChallenges(Long userId, int totalBooksRead, int totalPagesRead, int totalRatings) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        List<Challenge> challenges = challengeRepository.findAll();

        for (Challenge challenge : challenges) {
            boolean meetsCriteria = false;
            String category = challenge.getCategory().toLowerCase();

            switch (category) {
                case "books read":
                    meetsCriteria = totalBooksRead >= challenge.getTargetQuantity();
                    break;
                case "pages read":
                    meetsCriteria = totalPagesRead >= challenge.getTargetQuantity();
                    break;
                case "ratings given":
                    meetsCriteria = totalRatings >= challenge.getTargetQuantity();
                    break;
                default:
                    meetsCriteria = false;
            }

            if (meetsCriteria) {
                boolean alreadyHas = userChallengeRepository.existsByUserIdAndChallengeId(userId, challenge.getId());
                if (!alreadyHas) {
                    UserChallengeId userChallengeId = new UserChallengeId(user.getId(), challenge.getId());
                    UserChallenge userChallenge = new UserChallenge();
                    userChallenge.setId(userChallengeId);
                    userChallenge.setUser(user);
                    userChallenge.setChallenge(challenge);
                    userChallenge.setProgress(0);
                    userChallenge.setCompleted(false);

                    userChallengeRepository.save(userChallenge);
                }
            }
        }
    }
}