package uoc.tfg.cvelascofa.pageturner_backend.book.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.AuthorDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.AuthorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO created = authorService.create(authorDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAll() {
        List<AuthorDTO> authors = authorService.getAll();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getById(@PathVariable Long id) {
        Optional<AuthorDTO> authorDTO = authorService.getById(id);
        return authorDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        Optional<AuthorDTO> updated = authorService.update(id, authorDTO);
        return updated.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<AuthorDTO> author = authorService.getById(id);
        if (author.isPresent()) {
            authorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AuthorDTO>> searchAuthorsPageable(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<AuthorDTO> authors = authorService.searchAuthorsPageable(name, pageable);
        return ResponseEntity.ok(authors);
    }
}