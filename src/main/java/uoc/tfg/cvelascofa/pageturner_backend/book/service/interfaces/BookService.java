package uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDTO create(BookDTO bookDTO);
    BookDTO getById(Long id);
    Optional<Book> getBookEntityById(Long id);
    List<BookDTO> getAll();
    BookDTO update(Long id, BookDTO bookDTO);
    void delete(Long id);
    Page<BookDTO> searchBooksPageable(String title, Pageable pageable);
}