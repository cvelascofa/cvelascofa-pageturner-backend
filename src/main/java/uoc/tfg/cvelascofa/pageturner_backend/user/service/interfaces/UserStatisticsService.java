package uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserStatisticsDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

import java.util.List;

public interface UserStatisticsService {

    UserStatisticsDTO getUserStatistics(Long userId);
    void updateStatisticsWithProgress(User user, ReadingProgress progress);
    void createStatistics(User user);
    void updateRatingStats(User user, List<ReviewDTO> userReviews);
    void updateCompletedBooks(User user);

}