package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.MonthlyLeaderboard;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserStatisticsDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;

import java.util.List;

public interface UserStatisticsService {

    UserStatisticsDTO getUserStatistics(Long userId);
    void updateStatisticsWithProgress(User user, ReadingProgress progress);
    void createStatistics(User user);
    void updateRatingStats(User user, List<ReviewDTO> userReviews);
    void updateCompletedBooks(User user);
    void updateRankingThisMonth(User user, MonthlyLeaderboard leaderboard);
    void recalculateReadThisMonth(User user);
    void recalculateBooksFinishedThisMonth(User user);

}