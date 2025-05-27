package uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.user.dto.MonthlyLeaderboardDTO;

import java.util.List;

public interface MonthlyLeaderboardService {

    List<MonthlyLeaderboardDTO> getCurrentMonthLeaderboard();
    List<MonthlyLeaderboardDTO> getLeaderboardByMonthAndYear(int month, int year);

}
