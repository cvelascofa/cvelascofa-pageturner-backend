package uoc.tfg.cvelascofa.pageturner_backend.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.MonthlyLeaderboardDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.MonthlyLeaderboard;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonthlyLeaderboardMapper {

    MonthlyLeaderboardDTO toDto(MonthlyLeaderboard leaderboard);
    MonthlyLeaderboard toEntity(MonthlyLeaderboardDTO dto);
    List<MonthlyLeaderboardDTO> toDtoList(List<MonthlyLeaderboard> leaderboardList);

}
