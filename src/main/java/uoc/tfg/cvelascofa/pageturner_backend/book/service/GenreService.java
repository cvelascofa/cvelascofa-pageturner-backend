package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre createGenre(Genre genre);
    List<Genre> getAllGenres();
    Optional<Genre> getGenreById(Long id);
    void deleteGenre(Long id);

}
