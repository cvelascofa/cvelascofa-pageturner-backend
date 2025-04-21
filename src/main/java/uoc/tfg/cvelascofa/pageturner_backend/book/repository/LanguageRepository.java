package uoc.tfg.cvelascofa.pageturner_backend.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
