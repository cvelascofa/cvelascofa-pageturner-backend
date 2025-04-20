package uoc.tfg.cvelascofa.pageturner_backend.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.GenreDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.GenreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO) {
        GenreDTO createdGenre = genreService.createGenre(genreDTO);
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        Optional<GenreDTO> genreDTO = genreService.getGenreById(id);
        return genreDTO.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        Optional<GenreDTO> genreDTO = genreService.getGenreById(id);
        if (genreDTO.isPresent()) {
            genreService.deleteGenre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}