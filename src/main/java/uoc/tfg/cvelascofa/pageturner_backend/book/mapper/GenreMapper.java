package uoc.tfg.cvelascofa.pageturner_backend.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.GenreDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDTO toDTO(Genre genre);
    Genre toEntity(GenreDTO genreDTO);

}