package uoc.tfg.cvelascofa.pageturner_backend.challenge.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.dto.UserChallengeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.dto.UserChallengeWithBadgeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.entity.UserChallenge;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserChallengeMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "challenge.id", target = "challengeId")
    UserChallengeDTO toDto(UserChallenge userChallenge);

    @Mapping(source = "challenge.id", target = "challengeId")
    @Mapping(source = "challenge.name", target = "challengeName")
    @Mapping(source = "challenge.badgeUrl", target = "badgeUrl")
    UserChallengeWithBadgeDTO toUserChallengeWithBadgeDto(UserChallenge userChallenge);

    List<UserChallengeWithBadgeDTO> toUserChallengeWithBadgeDtoList(List<UserChallenge> userChallenges);

    List<UserChallengeDTO> toDtoList(List<UserChallenge> entities);

}