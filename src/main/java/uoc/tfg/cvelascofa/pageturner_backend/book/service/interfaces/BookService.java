package uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO create(BookDTO bookDTO);
    BookDTO getById(Long id);
    List<BookDTO> getAll();
    BookDTO update(Long id, BookDTO bookDTO);
    void delete(Long id);

}