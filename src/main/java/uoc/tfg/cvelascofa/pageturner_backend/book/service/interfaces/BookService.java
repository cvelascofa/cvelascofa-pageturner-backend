package uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO create(BookDTO bookDTO);
    BookDTO getById(Long id);
    List<BookDTO> getAll();
    BookDTO update(Long id, BookDTO bookDTO);
    void delete(Long id);
    Page<BookDTO> searchBooksPageable(String title, Pageable pageable);
}