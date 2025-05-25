package uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserStatisticsDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

public interface UserStatisticsService {

    UserStatisticsDTO getUserStatistics(Long userId);
    void updateStatistics(User user);
    void createStatistics(User user);

}