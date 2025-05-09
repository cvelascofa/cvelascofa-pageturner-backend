package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.AuthorDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.AuthorMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.BookMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.BookService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;
    private final PublisherRepository publisherRepository;
    private final EditionTypeRepository editionTypeRepository;

    @Override
    public BookDTO create(BookDTO bookDTO) {
        Genre genre = genreRepository.findById(bookDTO.getGenre().getId())
                .orElseThrow(() -> new RuntimeException("Genre not found with ID: " + bookDTO.getGenre().getId()));
        Language language = languageRepository.findById(bookDTO.getLanguage().getId())
                .orElseThrow(() -> new RuntimeException("Language not found with ID: " + bookDTO.getLanguage().getId()));
        Author author = authorRepository.findById(bookDTO.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + bookDTO.getAuthor().getId()));
        Publisher publisher = publisherRepository.findById(bookDTO.getPublisher().getId())
                .orElseThrow(() -> new RuntimeException("Publisher not found with ID: " + bookDTO.getPublisher().getId()));
        EditionType editionType = editionTypeRepository.findById(bookDTO.getEditionType().getId())
                .orElseThrow(() -> new RuntimeException("EditionType not found with ID: " + bookDTO.getEditionType().getId()));

        Book book = bookMapper.toEntity(bookDTO);

        book.setGenre(genre);
        book.setLanguage(language);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setEditionType(editionType);

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

        Language language = languageRepository.findById(bookDto.getLanguage().getId())
                .orElseThrow(() -> new RuntimeException("Language not found with id: " + bookDto.getLanguage().getId()));
        existingBook.setLanguage(language);

        Author author = authorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + bookDto.getAuthor().getId()));
        existingBook.setAuthor(author);

        Publisher publisher = publisherRepository.findById(bookDto.getPublisher().getId())
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + bookDto.getPublisher().getId()));
        existingBook.setPublisher(publisher);

        EditionType editionType = editionTypeRepository.findById(bookDto.getEditionType().getId())
                .orElseThrow(() -> new RuntimeException("EditionType not found with id: " + bookDto.getEditionType().getId()));
        existingBook.setEditionType(editionType);

        existingBook.setTitle(bookToUpdate.getTitle());
        existingBook.setDescription(bookToUpdate.getDescription());
        existingBook.setPublicationDate(bookToUpdate.getPublicationDate());
        existingBook.setIsbn(bookToUpdate.getIsbn());
        existingBook.setCoverImage(bookToUpdate.getCoverImage());
        existingBook.setPages(bookToUpdate.getPages());

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

    @Override
    public Optional<Book> getBookEntityById(Long id) {
        return bookRepository.findById(id);
    }
}