package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.BookRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.ReviewService;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.security.LibraryUserDetails;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserRepository;

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
            LibraryUserDetails userDetails = (LibraryUserDetails) authentication.getPrincipal();
            return userDetails.getId();
        }
        throw new RuntimeException("User not authenticated");
    }

    @PostMapping
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
    public ResponseEntity<ReviewDTO> update(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody ReviewDTO reviewDTO) {
        Optional<ReviewDTO> updated = reviewService.update(userId, bookId, reviewDTO);
        return updated.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userId}/{bookId}")
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