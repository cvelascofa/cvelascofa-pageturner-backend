package uoc.tfg.cvelascofa.pageturner_backend.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.entity.Challenge;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.entity.UserChallenge;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.entity.UserChallengeId;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.repository.ChallengeRepository;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.repository.UserChallengeRepository;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.service.interfaces.ChallengeService;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.MonthlyLeaderboard;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.UserStatistics;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.MonthlyLeaderboardRepository;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserRepository;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserStatisticsRepository;

import java.time.YearMonth;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedalsBatchService {

    private final MonthlyLeaderboardRepository leaderboardRepository;
    private final ChallengeRepository challengeRepository;
    private final UserChallengeRepository userChallengeRepository;
    private final UserRepository userRepository;
    private final UserStatisticsRepository userStatisticsRepository;
    private final ChallengeService challengeService;

    @Scheduled(cron = "0 5 0 1 * *")
    public void assignMedalsToTopUsers() {
        YearMonth previousMonth = YearMonth.now().minusMonths(1);
        int month = previousMonth.getMonthValue();
        int year = previousMonth.getYear();

        log.info("Assigning monthly medal challenges for {}/{}", month, year);

        List<MonthlyLeaderboard> top3 = leaderboardRepository
                .findTop3ByMonthAndYearOrderByRankingPositionAsc(month, year);

        if (top3.isEmpty()) {
            log.warn("No leaderboard data found for {}/{}", month, year);
            return;
        }

        Challenge gold = challengeRepository.findByName("Gold Rank").orElseThrow();
        Challenge silver = challengeRepository.findByName("Silver Rank").orElseThrow();
        Challenge bronze = challengeRepository.findByName("Bronze Rank").orElseThrow();

        for (MonthlyLeaderboard entry : top3) {
            Long userId = entry.getUserId();
            int position = entry.getRankingPosition();

            userRepository.findById(userId).ifPresent(user -> {
                Challenge challengeToAssign = switch (position) {
                    case 1 -> gold;
                    case 2 -> silver;
                    case 3 -> bronze;
                    default -> null;
                };

                if (challengeToAssign == null) return;

                UserChallengeId challengeId = new UserChallengeId(user.getId(), challengeToAssign.getId());

                if (!userChallengeRepository.existsById(challengeId)) {
                    UserChallenge userChallenge = new UserChallenge();
                    userChallenge.setId(challengeId);
                    userChallenge.setUser(user);
                    userChallenge.setChallenge(challengeToAssign);
                    userChallenge.setProgress(1);
                    userChallenge.setCompleted(true);
                    userChallengeRepository.save(userChallenge);
                    log.info("Assigned {} to user {}", challengeToAssign.getName(), user.getUsername());
                }
            });
        }
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void assignChallengesToAllUsers() {
        List<UserStatistics> allStats = userStatisticsRepository.findAll();

        for (UserStatistics stats : allStats) {
            Long userId = stats.getUser().getId();
            int totalBooksRead = stats.getTotalBooksRead();
            int totalPagesRead = stats.getTotalPagesRead();
            int totalRatings = stats.getTotalRatings();

            challengeService.checkAndAwardChallenges(userId, totalBooksRead, totalPagesRead, totalRatings);
        }
    }

}