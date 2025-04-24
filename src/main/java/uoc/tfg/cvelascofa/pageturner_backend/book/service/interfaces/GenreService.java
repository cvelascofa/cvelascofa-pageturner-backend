package uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.book.dto.GenreDTO;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    GenreDTO create(GenreDTO genreDTO);
    List<GenreDTO> getAll();
    Optional<GenreDTO> getById(Long id);
    Optional<GenreDTO> update(Long id, GenreDTO genreDTO);
    void delete(Long id);
    boolean isGenreInUse(Long genreId);

}