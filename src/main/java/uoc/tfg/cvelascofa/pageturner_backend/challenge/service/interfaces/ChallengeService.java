package uoc.tfg.cvelascofa.pageturner_backend.challenge.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.challenge.dto.ChallengeDTO;

import java.util.List;

public interface ChallengeService {

    ChallengeDTO createChallenge(ChallengeDTO challengeDTO);
    List<ChallengeDTO> getAllChallenges();
    ChallengeDTO getChallengeById(Long id);
    void checkAndAwardChallenges(Long userId, int totalBooksRead, int totalPagesRead, int totalRatings);

}