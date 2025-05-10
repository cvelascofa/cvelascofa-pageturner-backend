package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.BookRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReadingProgressDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.ReadingProgressService;
import uoc.tfg.cvelascofa.pageturner_backend.security.LibraryUserDetails;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/reading-progress/")
public class ReadingProgressController {

    @Autowired
    private ReadingProgressService readingProgressService;

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
    public ResponseEntity<ReadingProgressDTO> create(@RequestBody ReadingProgressDTO readingProgressDTO) {
        Long userId = getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(readingProgressDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        readingProgressDTO.setUserId(userId);
        readingProgressDTO.setBookId(book.getId());

        Optional<ReadingProgressDTO> existingProgress = readingProgressService.getByUserAndBook(user.getId(), book.getId());

        if (existingProgress.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ReadingProgressDTO created = readingProgressService.create(readingProgressDTO, user, book);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/{bookId}")
    public ResponseEntity<ReadingProgressDTO> getByUserAndBook(@PathVariable Long userId, @PathVariable Long bookId) {
        Optional<ReadingProgressDTO> readingProgressDTO = readingProgressService.getByUserAndBook(userId, bookId);
        return readingProgressDTO
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{userId}/{bookId}")
    public ResponseEntity<ReadingProgressDTO> update(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody ReadingProgressDTO readingProgressDTO) {
        Optional<ReadingProgressDTO> updatedProgress = readingProgressService.update(userId, bookId, readingProgressDTO);
        return updatedProgress.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userId}/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long bookId) {
        Optional<ReadingProgressDTO> readingProgress = readingProgressService.getByUserAndBook(userId, bookId);
        if (readingProgress.isPresent()) {
            readingProgressService.delete(userId, bookId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
