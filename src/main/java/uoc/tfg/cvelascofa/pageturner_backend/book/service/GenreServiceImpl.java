package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.GenreDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Genre;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.GenreMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.BookRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.GenreRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.GenreService;
import uoc.tfg.cvelascofa.pageturner_backend.exception.GenreInUseException;
import uoc.tfg.cvelascofa.pageturner_backend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public GenreDTO create(GenreDTO genreDTO) {
        Genre genre = genreMapper.toEntity(genreDTO);
        genre = genreRepository.save(genre);
        return genreMapper.toDTO(genre);
    }

    @Override
    public Optional<GenreDTO> update(Long id, GenreDTO genreDTO) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            Genre existingGenre = optionalGenre.get();
            existingGenre.setName(genreDTO.getName());
            Genre updatedGenre = genreRepository.save(existingGenre);
            return Optional.of(genreMapper.toDTO(updatedGenre));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<GenreDTO> getAll() {
        List<Genre> genres = genreRepository.findAll();

        return genres.stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GenreDTO> getById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.map(genreMapper::toDTO);
    }

    @Override
    public void delete(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            throw new ResourceNotFoundException("Genre not found");
        }

        if (bookRepository.existsByGenreId(id)) {
            throw new GenreInUseException("This genre is still referenced by books");
        }
        genreRepository.deleteById(id);
    }

    @Override
    public boolean isGenreInUse(Long genreId) {
        return bookRepository.existsByGenreId(genreId);
    }

}