package uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookEditionDTO;

import java.util.List;
import java.util.Optional;

public interface BookEditionService {

    BookEditionDTO create(BookEditionDTO bookEditionDTO);
    List<BookEditionDTO> getAll();
    Optional<BookEditionDTO> getById(Long id);
    BookEditionDTO update(Long id, BookEditionDTO bookEditionDTO);
    boolean delete(Long id);

}