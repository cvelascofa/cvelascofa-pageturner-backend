package uoc.tfg.cvelascofa.pageturner_backend.gamification.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.ChallengeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.entity.Challenge;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {

    ChallengeDTO toDto(Challenge challenge);
    Challenge toEntity(ChallengeDTO dto);
    List<ChallengeDTO> toDtoList(List<Challenge> challenges);

}