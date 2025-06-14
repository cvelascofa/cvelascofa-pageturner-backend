package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.service.interfaces.BookService;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity.Review;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.mapper.ReviewMapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.repository.ReviewRepository;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service.interfaces.ReviewService;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.UserService;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.UserStatisticsService;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private UserStatisticsService userStatisticsService;

    @Override
    public ReviewDTO create(ReviewDTO dto, User user, Book book) {
        Review review = reviewMapper.toEntity(dto);
        review.setUser(user);
        review.setBook(book);

        Review savedReview = reviewRepository.save(review);

        List<Review> userReviews = reviewRepository.findByUserId(user.getId());
        List<ReviewDTO> userReviewDTOs = userReviews.stream()
                .map(reviewMapper::toDTO)
                .toList();

        userStatisticsService.updateRatingStats(user, userReviewDTOs);
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

    @Override
    public List<ReviewDTO> getByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviewMapper.toDTOList(reviews);
    }

}