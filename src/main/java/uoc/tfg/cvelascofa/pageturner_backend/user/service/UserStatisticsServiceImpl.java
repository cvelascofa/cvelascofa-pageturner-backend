package uoc.tfg.cvelascofa.pageturner_backend.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository.ReadingProgressRepository;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserStatisticsDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.UserStatistics;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserStatisticsRepository;
import uoc.tfg.cvelascofa.pageturner_backend.user.mapper.UserStatisticsMapper;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.UserStatisticsService;

import java.util.NoSuchElementException;

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
    public void updateStatistics(User user) {
        // TODO: Calculate statistics
        UserStatistics stats = userStatisticsRepository.findByUserId(user.getId()).orElseGet(() -> {
            UserStatistics newStats = new UserStatistics();
            newStats.setUser(user);
            return newStats;
        });

        stats.setTotalBooksRead(0);
        stats.setTotalPagesRead(0);
        stats.setAverageRating(0.0);

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

}