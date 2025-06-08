package uoc.tfg.cvelascofa.pageturner_backend.gamification.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.MonthlyLeaderboardDTO;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.MonthlyLeaderboard;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonthlyLeaderboardMapper {

    MonthlyLeaderboardDTO toDto(MonthlyLeaderboard leaderboard);
    MonthlyLeaderboard toEntity(MonthlyLeaderboardDTO dto);
    List<MonthlyLeaderboardDTO> toDtoList(List<MonthlyLeaderboard> leaderboardList);

}
