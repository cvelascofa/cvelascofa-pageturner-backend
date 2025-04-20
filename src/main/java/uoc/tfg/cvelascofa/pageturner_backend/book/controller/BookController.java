package uoc.tfg.cvelascofa.pageturner_backend.book.controller;

import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.BookService;

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
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    public BookDTO update(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.update(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}