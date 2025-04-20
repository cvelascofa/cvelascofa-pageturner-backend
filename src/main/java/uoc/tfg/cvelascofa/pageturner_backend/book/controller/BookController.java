package uoc.tfg.cvelascofa.pageturner_backend.book.controller;

import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

}