package uoc.tfg.cvelascofa.pageturner_backend.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserStatisticsDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.UserStatistics;

@Mapper(componentModel = "spring")
public interface UserStatisticsMapper {

    @Mapping(source = "user.id", target = "userId")
    UserStatisticsDTO toDTO(UserStatistics entity);

    @Mapping(source = "userId", target = "user.id")
    UserStatistics toEntity(UserStatisticsDTO dto);

}