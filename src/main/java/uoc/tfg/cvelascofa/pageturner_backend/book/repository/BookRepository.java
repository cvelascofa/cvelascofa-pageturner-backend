package uoc.tfg.cvelascofa.pageturner_backend.book.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;

import java.util.Optional;

/**
 * Repository interface for {@link Book} entity.
 * This interface extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    boolean existsByGenreId(Long genreId);
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}