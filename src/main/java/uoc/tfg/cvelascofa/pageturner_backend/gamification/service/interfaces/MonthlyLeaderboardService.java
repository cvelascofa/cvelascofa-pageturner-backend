package uoc.tfg.cvelascofa.pageturner_backend.gamification.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.MonthlyLeaderboardDTO;

import java.util.List;

public interface MonthlyLeaderboardService {

    List<MonthlyLeaderboardDTO> getCurrentMonthLeaderboard();
    List<MonthlyLeaderboardDTO> getLeaderboardByMonthAndYear(int month, int year);

}
