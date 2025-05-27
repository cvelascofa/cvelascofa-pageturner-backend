package uoc.tfg.cvelascofa.pageturner_backend.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.enums.ReadingStatus;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository.ReadingProgressRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository.ReviewRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.ReviewService;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserStatisticsDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.UserStatistics;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserStatisticsRepository;
import uoc.tfg.cvelascofa.pageturner_backend.user.mapper.UserStatisticsMapper;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.UserStatisticsService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStatisticsServiceImpl implements UserStatisticsService {

    private final UserStatisticsRepository userStatisticsRepository;
    private final UserStatisticsMapper userStatisticsMapper;
    private final ReadingProgressRepository readingProgressRepository;

    @Override
    public UserStatisticsDTO getUserStatistics(Long userId) {
        UserStatistics statistics = userStatisticsRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("User statistics not found for user ID: " + userId));

        return userStatisticsMapper.toDTO(statistics);
    }

    @Override
    public void updateStatisticsWithProgress(User user, ReadingProgress newProgress) {
        UserStatistics stats = getOrCreateStats(user);

        if (stats.getLastReadingSession() == null ||
                newProgress.getProgressDate().isAfter(stats.getLastReadingSession().atStartOfDay())) {
            stats.setLastReadingSession(newProgress.getProgressDate().toLocalDate());
        }

        int updatedPages = stats.getTotalPagesRead() + (newProgress.getPagesRead() != null ? newProgress.getPagesRead() : 0);
        stats.setTotalPagesRead(updatedPages);

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
        List<ReadingProgress> completedBooks = readingProgressRepository.findByUserIdAndReadingStatus(user.getId(), ReadingStatus.COMPLETED);
        int totalCompleted = completedBooks.size();
        UserStatistics stats = getOrCreateStats(user);
        stats.setTotalBooksRead(totalCompleted);
        userStatisticsRepository.save(stats);
    }

}