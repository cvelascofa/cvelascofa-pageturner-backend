package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.repository.BookRepository;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.ReadingProgressDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service.interfaces.ReadingProgressService;
import uoc.tfg.cvelascofa.pageturner_backend.security.CustomUserDetails;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reading-progress/")
@PreAuthorize("isAuthenticated()")
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
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
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
        ReadingProgressDTO created = readingProgressService.create(readingProgressDTO, user, book);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/{bookId}")
    public ResponseEntity<List<ReadingProgressDTO>> getAllByUserAndBook(@PathVariable Long userId, @PathVariable Long bookId) {
        List<ReadingProgressDTO> progressList = readingProgressService.getAllByUserAndBook(userId, bookId);
        if (progressList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(progressList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadingProgressDTO> update(@PathVariable Long id, @RequestBody ReadingProgressDTO readingProgressDTO) {
        Optional<ReadingProgressDTO> updatedProgress = readingProgressService.update(id, readingProgressDTO);
        return updatedProgress.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<ReadingProgressDTO> readingProgress = readingProgressService.getById(id);
        if (readingProgress.isPresent()) {
            readingProgressService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paginated/{userId}/{bookId}")
    public ResponseEntity<Page<ReadingProgressDTO>> getPaginatedByUserAndBook(
            @PathVariable Long userId,
            @PathVariable Long bookId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by("progressDate").ascending()
                : Sort.by("progressDate").descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ReadingProgressDTO> pageResult = readingProgressService
                .getPaginatedByUserAndBook(userId, bookId, pageable);

        return ResponseEntity.ok(pageResult);
    }

}