package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Genre;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.BookMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.BookRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.GenreRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDTO create(BookDTO bookDTO) {
        // Obtener el género asociado al libro desde el DTO
        Genre genre = genreRepository.findById(bookDTO.getGenre().getId())
                .orElseThrow(() -> new RuntimeException("Genre not found with ID: " + bookDTO.getGenre().getId()));

        // Convertir el DTO a la entidad Book, asignando el género
        Book book = bookMapper.toEntity(bookDTO);
        book.setGenre(genre);

        // Guardar el libro en la base de datos
        Book savedBook = bookRepository.save(book);

        // Retornar el DTO del libro creado
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

        existingBook.setTitle(bookToUpdate.getTitle());
        existingBook.setDescription(bookToUpdate.getDescription());
        existingBook.setPublicationDate(bookToUpdate.getPublicationDate());  // Usar la fecha completa

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