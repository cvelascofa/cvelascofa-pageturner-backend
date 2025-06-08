package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorDTO create(AuthorDTO authorDTO);
    List<AuthorDTO> getAll();
    Optional<AuthorDTO> getById(Long id);
    Optional<AuthorDTO> update(Long id, AuthorDTO authorDTO);
    void delete(Long id);
    Page<AuthorDTO> searchAuthorsPageable(String name, Pageable pageable);

}
