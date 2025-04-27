package uoc.tfg.cvelascofa.pageturner_backend.book.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "authors", source = "authors")
    @Mapping(target = "bookEditions", source = "bookEditions")
    BookDTO toDTO(Book book);

    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "bookEditions", ignore = true)
    Book toEntity(BookDTO bookDTO);

}