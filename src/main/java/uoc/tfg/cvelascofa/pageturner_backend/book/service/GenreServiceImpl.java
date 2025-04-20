package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.GenreDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Genre;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.GenreMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.genreDTOToGenre(genreDTO);
        genre = genreRepository.save(genre);
        return genreMapper.genreToGenreDTO(genre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();

        return genres.stream()
                .map(genreMapper::genreToGenreDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GenreDTO> getGenreById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.map(genreMapper::genreToGenreDTO);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

}
