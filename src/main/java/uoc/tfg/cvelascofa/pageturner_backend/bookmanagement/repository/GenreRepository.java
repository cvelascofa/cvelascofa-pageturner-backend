package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Genre;

/**
 * Repository interface for {@link Genre} entity.
 * This interface extends JpaRepository to provide CRUD operations.
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Page<Genre> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
