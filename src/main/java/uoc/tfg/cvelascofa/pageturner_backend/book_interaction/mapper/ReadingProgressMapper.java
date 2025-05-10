package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReadingProgressDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.mapper.helper.ReadingStatusMapperHelper;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingProgressId;

//@Mapper(componentModel = "spring", uses = ReadingStatusMapperHelper.class)
@Mapper(componentModel = "spring")
public interface ReadingProgressMapper {

    //@Mapping(source = "readingStatus", target = "readingStatus")
    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.bookId", target = "bookId")
    ReadingProgressDTO toDTO(ReadingProgress entity);

    //@Mapping(source = "readingStatus", target = "readingStatus")
    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "bookId", target = "id.bookId")
    ReadingProgress toEntity(ReadingProgressDTO dto);
}
