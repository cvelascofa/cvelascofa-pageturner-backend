package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.ReadingProgressDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.enums.ReadingStatus;

@Mapper(componentModel = "spring")
public interface ReadingProgressMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "readingStatus", target = "readingStatus")
    ReadingProgressDTO toDTO(ReadingProgress entity);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "readingStatus", expression = "java(toReadingStatus(dto.getReadingStatus()))")
    ReadingProgress toEntity(ReadingProgressDTO dto);

    default ReadingStatus toReadingStatus(String status) {
        if (status == null) return null;
        try {
            return ReadingStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid reading status: " + status);
        }
    }
}