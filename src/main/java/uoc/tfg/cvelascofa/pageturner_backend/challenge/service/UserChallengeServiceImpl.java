package uoc.tfg.cvelascofa.pageturner_backend.challenge.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.dto.UserChallengeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.dto.UserChallengeWithBadgeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.entity.Challenge;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.entity.UserChallenge;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.entity.UserChallengeId;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.mapper.UserChallengeMapper;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.repository.ChallengeRepository;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.repository.UserChallengeRepository;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.service.interfaces.UserChallengeService;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserChallengeServiceImpl implements UserChallengeService {

    private final UserChallengeRepository userChallengeRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final UserChallengeMapper userChallengeMapper;

    @Override
    public UserChallengeDTO createUserChallenge(UserChallengeDTO dto) {
        Long userId = dto.getUserId();
        Long challengeId = dto.getChallengeId();

        UserChallengeId id = new UserChallengeId(userId, challengeId);

        if (userChallengeRepository.existsById(id)) {
            throw new IllegalStateException("User already completed this challenge");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));

        UserChallenge userChallenge = new UserChallenge();
        userChallenge.setId(id);
        userChallenge.setUser(user);
        userChallenge.setChallenge(challenge);

        return userChallengeMapper.toDto(userChallengeRepository.save(userChallenge));
    }

    @Override
    public List<UserChallengeDTO> getUserChallenges(Long userId) {
        List<UserChallenge> entities = userChallengeRepository.findByUserId(userId);
        return userChallengeMapper.toDtoList(entities);
    }

    @Override
    public Page<UserChallengeWithBadgeDTO> getUserChallengesWithBadges(Long userId, int page, int size, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "createdAt"));
        return userChallengeRepository.findByUserId(userId, pageable)
                .map(userChallengeMapper::toUserChallengeWithBadgeDto);
    }

    @Override
    public boolean hasUserCompletedChallenge(Long userId, Long challengeId) {
        return userChallengeRepository.existsByUserIdAndChallengeId(userId, challengeId);
    }

}