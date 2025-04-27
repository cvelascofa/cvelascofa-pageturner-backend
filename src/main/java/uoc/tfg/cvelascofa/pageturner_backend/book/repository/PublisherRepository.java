package uoc.tfg.cvelascofa.pageturner_backend.book.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Page<Publisher> findByNameContainingIgnoreCase(String name, Pageable pageable);
}