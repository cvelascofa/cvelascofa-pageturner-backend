package uoc.tfg.cvelascofa.pageturner_backend.book.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, AuthorMapper.class, LanguageMapper.class, PublisherMapper.class, EditionTypeMapper.class})
public interface BookMapper {

    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "language", source = "language")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "publisher", source = "publisher")
    @Mapping(target = "editionType", source = "editionType")
    Book toEntity(BookDTO bookDTO);

    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "language", source = "language")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "publisher", source = "publisher")
    @Mapping(target = "editionType", source = "editionType")
    BookDTO toDTO(Book book);
}