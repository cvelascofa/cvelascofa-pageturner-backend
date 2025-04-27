package uoc.tfg.cvelascofa.pageturner_backend.book.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.AuthorDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Author;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO toDTO(Author author);
    Author toEntity(AuthorDTO authorDTO);
    List<AuthorDTO> toDTOList(List<Author> authors);

}