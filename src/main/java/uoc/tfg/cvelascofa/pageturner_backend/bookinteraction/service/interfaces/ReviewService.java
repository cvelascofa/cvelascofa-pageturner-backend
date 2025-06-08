package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    ReviewDTO create(ReviewDTO reviewDTO, User user, Book book);
    List<ReviewDTO> getAll();
    Optional<ReviewDTO> getById(Long id);
    Optional<ReviewDTO> update(Long userId, Long bookId, ReviewDTO reviewDTO);
    void delete(Long userId, Long bookId);
    Optional<ReviewDTO> getByUserAndBook(Long userId, Long bookId);
    List<ReviewDTO> getByBookId(Long bookId);
    List<ReviewDTO> getByUserId(Long userId);

}