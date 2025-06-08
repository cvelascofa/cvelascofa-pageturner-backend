package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.enums.ReadingStatus;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.repository.ReadingProgressRepository;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.service.interfaces.ChallengeService;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserStatisticsDTO;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.MonthlyLeaderboard;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.UserStatistics;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.repository.MonthlyLeaderboardRepository;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository.UserStatisticsRepository;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.mapper.UserStatisticsMapper;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.UserStatisticsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserStatisticsServiceImpl implements UserStatisticsService {

    private final UserStatisticsRepository userStatisticsRepository;
    private final UserStatisticsMapper userStatisticsMapper;
    private final ReadingProgressRepository readingProgressRepository;
    private final MonthlyLeaderboardRepository leaderboardRepository;
    private final ChallengeService challengeService;

    @Override
    public UserStatisticsDTO getUserStatistics(Long userId) {
        UserStatistics statistics = userStatisticsRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("User statistics not found for user ID: " + userId));

        return userStatisticsMapper.toDTO(statistics);
    }

    @Override
    public void updateStatisticsWithProgress(User user, ReadingProgress newProgress) {
        UserStatistics stats = getOrCreateStats(user);

        int pagesRead = newProgress.getPagesRead() != null ? newProgress.getPagesRead() : 0;
        stats.setTotalPagesRead(stats.getTotalPagesRead() + pagesRead);

        if (newProgress.getProgressDate() != null) {
            LocalDate progressDate = newProgress.getProgressDate().toLocalDate();
            LocalDate now = LocalDate.now();

            boolean isSameMonthAndYear =
                    progressDate.getMonthValue() == now.getMonthValue() &&
                            progressDate.getYear() == now.getYear();

            if (isSameMonthAndYear) {
                stats.setPagesReadThisMonth(stats.getPagesReadThisMonth() + pagesRead);
            }
        }

        userStatisticsRepository.save(stats);

        challengeService.checkAndAwardChallenges(
                user.getId(),
                stats.getTotalBooksRead(),
                stats.getTotalPagesRead(),
                stats.getTotalRatings()
        );
    }

    @Override
    public void recalculateReadThisMonth(User user) {
        UserStatistics stats = getOrCreateStats(user);

        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        int pagesReadThisMonth = readingProgressRepository
                .sumPagesReadByUserIdAndMonth(user.getId(), currentMonth, currentYear)
                .orElse(0);

        stats.setPagesReadThisMonth(pagesReadThisMonth);

        userStatisticsRepository.save(stats);
    }

    @Override
    public void createStatistics(User user) {
        UserStatistics stats = new UserStatistics();
        stats.setUser(user);
        stats.setTotalBooksRead(0);
        stats.setTotalPagesRead(0);
        stats.setAverageRating(0.0);

        userStatisticsRepository.save(stats);
    }

    @Override
    public void updateRatingStats(User user, List<ReviewDTO> userReviews) {
        int totalRatings = userReviews.size();
        double averageRating = 0.0;

        if (totalRatings > 0) {
            double sumRatings = userReviews.stream()
                    .mapToInt(ReviewDTO::getRating)
                    .sum();
            averageRating = sumRatings / totalRatings;
        }

        UserStatistics stats = this.getOrCreateStats(user);

        stats.setTotalRatings(totalRatings);
        stats.setAverageRating(averageRating);

        userStatisticsRepository.save(stats);

        challengeService.checkAndAwardChallenges(
                user.getId(),
                stats.getTotalBooksRead(),
                stats.getTotalPagesRead(),
                stats.getTotalRatings()
        );

    }

    private UserStatistics getOrCreateStats(User user) {
        return userStatisticsRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    UserStatistics newStats = new UserStatistics();
                    newStats.setUser(user);
                    newStats.setTotalBooksRead(0);
                    newStats.setTotalPagesRead(0);
                    newStats.setAverageRating(0.0);
                    newStats.setTotalRatings(0);
                    return newStats;
                });
    }

    @Override
    public void updateCompletedBooks(User user) {
        List<ReadingProgress> completedBooks = readingProgressRepository
                .findByUserIdAndReadingStatus(user.getId(), ReadingStatus.COMPLETED);

        int totalCompleted = completedBooks.size();

        YearMonth currentMonth = YearMonth.now();
        int booksThisMonth = (int) completedBooks.stream()
                .filter(book -> {
                    LocalDateTime progressDate = book.getProgressDate();
                    return progressDate != null &&
                            YearMonth.from(progressDate).equals(currentMonth);
                })
                .count();

        UserStatistics stats = getOrCreateStats(user);
        stats.setTotalBooksRead(totalCompleted);
        stats.setBooksReadThisMonth(booksThisMonth);

        userStatisticsRepository.save(stats);

        challengeService.checkAndAwardChallenges(
                user.getId(),
                stats.getTotalBooksRead(),
                stats.getTotalPagesRead(),
                stats.getTotalRatings()
        );
    }

    public void updateRankingThisMonth(User user, MonthlyLeaderboard leaderboard) {
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        if (leaderboard != null) {
            UserStatistics stats = getOrCreateStats(user);
            stats.setRankingThisMonth(leaderboard.getRankingPosition());
            userStatisticsRepository.save(stats);
        }
    }

    @Override
    public void recalculateBooksFinishedThisMonth(User user) {
        UserStatistics stats = getOrCreateStats(user);

        YearMonth currentMonth = YearMonth.now();

        List<ReadingProgress> completedThisMonth = readingProgressRepository
                .findByUserIdAndReadingStatus(user.getId(), ReadingStatus.COMPLETED)
                .stream()
                .filter(progress -> {
                    LocalDateTime date = progress.getProgressDate();
                    return date != null && YearMonth.from(date).equals(currentMonth);
                })
                .toList();

        stats.setBooksReadThisMonth(completedThisMonth.size());

        userStatisticsRepository.save(stats);
    }


}