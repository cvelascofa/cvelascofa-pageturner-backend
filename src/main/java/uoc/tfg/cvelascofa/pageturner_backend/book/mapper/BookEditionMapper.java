package uoc.tfg.cvelascofa.pageturner_backend.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookEditionDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.BookEdition;

@Mapper(componentModel = "spring")
public interface BookEditionMapper {

    @Mappings({
            @Mapping(source = "book.id", target = "bookId"),
            @Mapping(source = "publisher.id", target = "publisherId"),
            @Mapping(source = "editionType.id", target = "editionTypeId"),
            @Mapping(source = "language.id", target = "languageId")
    })
    BookEditionDTO toDTO(BookEdition bookEdition);

    @Mappings({
            @Mapping(source = "bookId", target = "book.id"),
            @Mapping(source = "publisherId", target = "publisher.id"),
            @Mapping(source = "editionTypeId", target = "editionType.id"),
            @Mapping(source = "languageId", target = "language.id")
    })
    BookEdition toEntity(BookEditionDTO bookEditionDTO);

}