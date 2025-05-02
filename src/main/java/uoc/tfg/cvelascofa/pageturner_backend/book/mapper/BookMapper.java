package uoc.tfg.cvelascofa.pageturner_backend.book.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;

@Mapper(componentModel = "spring")
public interface  BookMapper {

    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "author", source = "author")
    Book toEntity(BookDTO bookDTO);

    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "author", source = "author")
    BookDTO toDTO(Book book);

}