package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.GenreDTO;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    GenreDTO create(GenreDTO genreDTO);
    List<GenreDTO> getAll();
    Optional<GenreDTO> getById(Long id);
    Optional<GenreDTO> update(Long id, GenreDTO genreDTO);
    void delete(Long id);
    Page<GenreDTO> searchGenresPageable(String name, Pageable pageable);

}