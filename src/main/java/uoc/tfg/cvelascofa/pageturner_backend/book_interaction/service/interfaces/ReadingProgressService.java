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
    Optional<ReadingProgressDTO> getByUserAndBook(Long userId, Long bookId);
    Optional<ReadingProgressDTO> update(Long userId, Long bookId, ReadingProgressDTO readingProgressDTO);
    void delete(Long userId, Long bookId);

}