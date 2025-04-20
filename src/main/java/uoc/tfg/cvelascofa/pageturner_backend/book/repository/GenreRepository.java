package uoc.tfg.cvelascofa.pageturner_backend.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}