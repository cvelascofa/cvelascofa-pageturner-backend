package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.GenreDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDTO(Genre genre);
    Genre toEntity(GenreDTO genreDTO);

}