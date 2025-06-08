package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.AuthorDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Author;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO toDTO(Author author);
    Author toEntity(AuthorDTO authorDTO);
    List<AuthorDTO> toDTOList(List<Author> authors);

}