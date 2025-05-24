package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReadingProgressDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReadingProgressService {

    ReadingProgressDTO create(ReadingProgressDTO readingProgressDTO, User user, Book book);
    List<ReadingProgressDTO> getAllByUserAndBook(Long userId, Long bookId);
    void deleteById(Long id);
    Optional<ReadingProgressDTO> getById(Long id);
    Optional<ReadingProgressDTO> update(Long id, ReadingProgressDTO readingProgressDTO);
    Page<ReadingProgressDTO> getPaginatedByUserAndBook(Long userId, Long bookId, Pageable pageable);

}