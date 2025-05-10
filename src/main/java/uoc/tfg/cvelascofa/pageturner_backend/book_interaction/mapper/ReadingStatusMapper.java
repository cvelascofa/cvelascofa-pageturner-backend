package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReadingStatusDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingStatus;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReadingStatusMapper {

    ReadingStatusDTO toDTO(ReadingStatus readingStatus);
    ReadingStatus toEntity(ReadingStatusDTO readingStatusDTO);
    List<ReadingStatusDTO> toDTOList(List<ReadingStatus> readingStatuses);

}
