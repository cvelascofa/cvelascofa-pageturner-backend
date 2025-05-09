package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.BookService;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.Review;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.mapper.ReviewMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository.ReviewRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.ReviewService;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    @Override
    public ReviewDTO create(ReviewDTO dto, User user, Book book) {
        Review review = reviewMapper.toEntity(dto);
        review.setUser(user);
        review.setBook(book);

        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toDTO(savedReview);
    }

    @Override
    public List<ReviewDTO> getAll() {
        return reviewMapper.toDTOList(reviewRepository.findAll());
    }

    @Override
    public Optional<ReviewDTO> getById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDTO);
    }

    @Override
    public Optional<ReviewDTO> update(Long userId, Long bookId, ReviewDTO dto) {
        Optional<Review> optionalReview = reviewRepository.findByUserIdAndBookId(userId, bookId);
        if (!optionalReview.isPresent()) {
            return Optional.empty();
        }

        Review review = optionalReview.get();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        Optional<User> userOptional = userService.getById(dto.getUserId());
        Optional<Book> bookOptional = bookService.getBookEntityById(dto.getBookId());

        if (userOptional.isEmpty() || bookOptional.isEmpty()) {
            throw new IllegalArgumentException("User or book not found");
        }

        review.setUser(userOptional.get());
        review.setBook(bookOptional.get());

        return Optional.of(reviewMapper.toDTO(reviewRepository.save(review)));
    }

    @Override
    public void delete(Long userId, Long bookId) {
        reviewRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    @Override
    public Optional<ReviewDTO> getByUserAndBook(Long userId, Long bookId) {
        return reviewRepository.findByUserIdAndBookId(userId, bookId)
                .map(reviewMapper::toDTO);
    }

    public List<ReviewDTO> getByBookId(Long bookId) {
        List<Review> reviews = reviewRepository.findByBookId(bookId);
        return reviewMapper.toDTOList(reviews);
    }

}