package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import uoc.tfg.cvelascofa.pageturner_backend.book.dto.GenreDTO;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    GenreDTO createGenre(GenreDTO genreDTO);
    List<GenreDTO> getAllGenres();
    Optional<GenreDTO> getGenreById(Long id);
    void deleteGenre(Long id);

}