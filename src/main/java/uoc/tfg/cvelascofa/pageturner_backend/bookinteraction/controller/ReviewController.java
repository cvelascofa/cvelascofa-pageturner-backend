package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.repository.BookRepository;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service.interfaces.ReviewService;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.security.CustomUserDetails;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getId();
        }
        throw new RuntimeException("User not authenticated");
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReviewDTO> create(@RequestBody ReviewDTO reviewDTO) {
        Long userId = getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(reviewDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        reviewDTO.setUserId(userId);
        reviewDTO.setBookId(book.getId());

        ReviewDTO created = reviewService.create(reviewDTO, user, book);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAll() {
        List<ReviewDTO> reviews = reviewService.getAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{bookId}")
    public ResponseEntity<ReviewDTO> getByUserAndBook(@PathVariable Long userId, @PathVariable Long bookId) {
        Optional<ReviewDTO> reviewDTO = reviewService.getByUserAndBook(userId, bookId);
        return reviewDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{userId}/{bookId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReviewDTO> update(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody ReviewDTO reviewDTO) {
        Optional<ReviewDTO> updated = reviewService.update(userId, bookId, reviewDTO);
        return updated.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userId}/{bookId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long bookId) {
        Optional<ReviewDTO> review = reviewService.getByUserAndBook(userId, bookId);
        if (review.isPresent()) {
            reviewService.delete(userId, bookId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ReviewDTO>> getAllByBookId(@PathVariable Long bookId) {
        List<ReviewDTO> reviews = reviewService.getByBookId(bookId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}