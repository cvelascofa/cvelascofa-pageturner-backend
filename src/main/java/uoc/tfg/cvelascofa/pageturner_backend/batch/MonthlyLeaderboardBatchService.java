package uoc.tfg.cvelascofa.pageturner_backend.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.repository.ReadingProgressRepository;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.MonthlyLeaderboard;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.mapper.MonthlyLeaderboardMapper;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.repository.MonthlyLeaderboardRepository;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository.UserRepository;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.UserStatisticsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonthlyLeaderboardBatchService {

    private final UserRepository userRepository;
    private final ReadingProgressRepository readingProgressRepository;
    private final MonthlyLeaderboardRepository leaderboardRepository;
    private final MonthlyLeaderboardMapper monthlyLeaderboardMapper;
    private final UserStatisticsService userStatisticsService;

    @Scheduled(cron = "0 0 0,12 * * *")
    public void createMonthlyLeaderboardForAllUsers() {
        int month = java.time.LocalDate.now().getMonthValue();
        int year = java.time.LocalDate.now().getYear();

        List<MonthlyLeaderboard> leaderboard = leaderboardRepository.findAllByMonthAndYearOrderByPagesReadDesc(month, year);
        List<MonthlyLeaderboard> entries = new ArrayList<>();

        var users = userRepository.findAll();

        for (var user : users) {
            boolean exists = leaderboardRepository.findByUserIdAndMonthAndYear(user.getId(), month, year).isPresent();
            if (!exists) {
                int pagesRead = readingProgressRepository.sumPagesReadByUserIdAndMonth(user.getId(), month, year).orElse(0);

                MonthlyLeaderboard entry = new MonthlyLeaderboard();
                entry.setUserId(user.getId());
                entry.setMonth(month);
                entry.setYear(year);
                entry.setPagesRead(pagesRead);
                entry.setRankingPosition(0);
                entry.setUpdatedAt(LocalDateTime.now());

                entries.add(entry);
            }
        }

        leaderboardRepository.saveAll(entries);
        var leaderboardDtos = monthlyLeaderboardMapper.toDtoList(leaderboard);
        log.info("🔥🏆 Generated {} leaderboard entries for {}/{}! 📚🚀", leaderboardDtos.size(), month, year);
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void updateAllLeaderboards() {
        List<MonthlyLeaderboard> allLeaderboards = leaderboardRepository.findAll();

        for (MonthlyLeaderboard leaderboard : allLeaderboards) {
            Long userId = leaderboard.getUserId();
            int month = leaderboard.getMonth();
            int year = leaderboard.getYear();

            int updatedPagesRead = readingProgressRepository
                    .sumPagesReadByUserIdAndMonth(userId, month, year)
                    .orElse(0);

            leaderboard.setPagesRead(updatedPagesRead);
            leaderboard.setUpdatedAt(LocalDateTime.now());
        }

        allLeaderboards.sort((a, b) -> Integer.compare(b.getPagesRead(), a.getPagesRead()));

        leaderboardRepository.saveAll(allLeaderboards);
        log.info("🔄 Updated {} leaderboard entries with recalculated pages read!", allLeaderboards.size());
    }

    @Scheduled(cron = "0 0/10 * * * *")
    @Scheduled(cron = "0 * * * * *")
    public void updateCurrentMonthLeaderboards() {
        int month = java.time.LocalDate.now().getMonthValue();
        int year = java.time.LocalDate.now().getYear();

        List<MonthlyLeaderboard> currentLeaderboards = leaderboardRepository.findAllByMonthAndYearOrderByPagesReadDesc(month, year);


        for (MonthlyLeaderboard leaderboard : currentLeaderboards) {
            Long userId = leaderboard.getUserId();
            var user = userRepository.findById(userId).orElse(null);

            int updatedPagesRead = readingProgressRepository
                    .sumPagesReadByUserIdAndMonth(userId, month, year)
                    .orElse(0);
            leaderboard.setPagesRead(updatedPagesRead);
        }

        currentLeaderboards.sort((a, b) -> Integer.compare(b.getPagesRead(), a.getPagesRead()));

        int position = 1;
        for (MonthlyLeaderboard leaderboard : currentLeaderboards) {
            leaderboard.setRankingPosition(position++);
            leaderboard.setUpdatedAt(LocalDateTime.now());

            Long userId = leaderboard.getUserId();
            var user = userRepository.findById(userId).orElse(null);

            if (user != null) {
                userStatisticsService.updateRankingThisMonth(user, leaderboard);
                userStatisticsService.recalculateReadThisMonth(user);
                userStatisticsService.recalculateBooksFinishedThisMonth(user);
            }
        }

        leaderboardRepository.saveAll(currentLeaderboards);
        log.info("⏱️ Updated {} current month leaderboard entries for {}/{}", currentLeaderboards.size(), month, year);
    }
}