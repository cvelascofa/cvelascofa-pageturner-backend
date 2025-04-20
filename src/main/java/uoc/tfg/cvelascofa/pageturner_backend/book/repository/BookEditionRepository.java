package uoc.tfg.cvelascofa.pageturner_backend.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.BookEdition;

@Repository
public interface BookEditionRepository extends JpaRepository<BookEdition, Long> {

}