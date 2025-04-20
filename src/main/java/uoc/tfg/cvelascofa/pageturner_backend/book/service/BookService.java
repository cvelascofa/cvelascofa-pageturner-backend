package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO bookDTO);
    BookDTO getBookById(Long id);
    List<BookDTO> getAllBooks();
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);

}