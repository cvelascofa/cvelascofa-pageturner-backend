package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.GenreDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.service.interfaces.GenreService;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.exception.GenreInUseException;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<GenreDTO> create(@RequestBody GenreDTO genreDTO) {
        GenreDTO createdGenre = genreService.create(genreDTO);
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {
        List<GenreDTO> genres = genreService.getAll();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getById(@PathVariable Long id) {
        Optional<GenreDTO> genreDTO = genreService.getById(id);
        return genreDTO.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        Optional<GenreDTO> updatedGenre = genreService.update(id, genreDTO);
        return updatedGenre
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            genreService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (GenreInUseException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<GenreDTO>> searchGenresPageable(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<GenreDTO> genres = genreService.searchGenresPageable(name, pageable);
        return ResponseEntity.ok(genres);
    }
}