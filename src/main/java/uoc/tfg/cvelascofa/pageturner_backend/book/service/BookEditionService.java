package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookEditionDTO;

import java.util.List;
import java.util.Optional;

public interface BookEditionService {

    BookEditionDTO createBookEdition(BookEditionDTO bookEditionDTO);

    List<BookEditionDTO> getAllBookEditions();

    Optional<BookEditionDTO> getBookEditionById(Long id);

    BookEditionDTO updateBookEdition(Long id, BookEditionDTO bookEditionDTO);

    boolean deleteBookEdition(Long id);

}