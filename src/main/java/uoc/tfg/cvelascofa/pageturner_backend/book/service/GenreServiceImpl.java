package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Genre;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

}
