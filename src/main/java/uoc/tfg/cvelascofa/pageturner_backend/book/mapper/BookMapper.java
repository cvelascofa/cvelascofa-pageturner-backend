package uoc.tfg.cvelascofa.pageturner_backend.book.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "genre.id", target = "genreId")
    BookDTO toDTO(Book book);

    @Mapping(source = "genreId", target = "genre.id")
    Book toEntity(BookDTO bookDTO);

}