package uoc.tfg.cvelascofa.pageturner_backend.gamification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.MonthlyLeaderboardDTO;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.MonthlyLeaderboard;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.mapper.MonthlyLeaderboardMapper;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.repository.MonthlyLeaderboardRepository;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.service.interfaces.MonthlyLeaderboardService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlyLeaderboardServiceImpl implements MonthlyLeaderboardService {

    private final MonthlyLeaderboardRepository leaderboardRepository;
    private final MonthlyLeaderboardMapper monthlyLeaderboardMapper;

    @Override
    public List<MonthlyLeaderboardDTO> getCurrentMonthLeaderboard() {
        LocalDate now = LocalDate.now();
        return getLeaderboardByMonthAndYear(now.getMonthValue(), now.getYear());
    }

    @Override
    public List<MonthlyLeaderboardDTO> getLeaderboardByMonthAndYear(int month, int year) {
        List<MonthlyLeaderboard> leaders = leaderboardRepository.findAllByMonthAndYearOrderByPagesReadDesc(month, year);
        return monthlyLeaderboardMapper.toDtoList(leaders);
    }

}