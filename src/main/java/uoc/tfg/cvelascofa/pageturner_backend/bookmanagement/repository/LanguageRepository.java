package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Page<Language> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
