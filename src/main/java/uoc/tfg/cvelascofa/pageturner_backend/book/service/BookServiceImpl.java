package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.AuthorDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Author;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Genre;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.BookMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.AuthorRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.BookRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.GenreRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.BookService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    @Override
    public BookDTO create(BookDTO bookDTO) {
        Genre genre = genreRepository.findById(bookDTO.getGenre().getId())
                .orElseThrow(() -> new RuntimeException("Genre not found with ID: " + bookDTO.getGenre().getId()));
        Author author = authorRepository.findById(bookDTO.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + bookDTO.getAuthor().getId()));

        Book book = bookMapper.toEntity(bookDTO);

        book.setAuthor(author);
        book.setGenre(genre);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public BookDTO getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return bookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO update(Long id, BookDTO bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        Book bookToUpdate = bookMapper.toEntity(bookDto);

        Genre genre = genreRepository.findById(bookDto.getGenre().getId())
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + bookDto.getGenre().getId()));
        existingBook.setGenre(genre);

        Author author = authorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + bookDto.getAuthor().getId()));
        existingBook.setAuthor(author);

        existingBook.setTitle(bookToUpdate.getTitle());
        existingBook.setDescription(bookToUpdate.getDescription());
        existingBook.setPublicationDate(bookToUpdate.getPublicationDate());

        Book updatedBook = bookRepository.save(existingBook);

        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<BookDTO> searchBooksPageable(String title, Pageable pageable) {
        Page<Book> booksPage = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        return booksPage.map(bookMapper::toDTO);
    }
}