package uoc.tfg.cvelascofa.pageturner_backend.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookEditionDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.BookEditionService;

import java.util.List;

@RestController
@RequestMapping("/book-editions")
public class BookEditionController {

    @Autowired
    private BookEditionService bookEditionService;

    @PostMapping
    public ResponseEntity<BookEditionDTO> create(@RequestBody BookEditionDTO bookEditionDTO) {
        BookEditionDTO created = bookEditionService.create(bookEditionDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<BookEditionDTO>> getAll() {
        return ResponseEntity.ok(bookEditionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookEditionDTO> getById(@PathVariable Long id) {
        return bookEditionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookEditionDTO> update(@PathVariable Long id, @RequestBody BookEditionDTO bookEditionDTO) {
        BookEditionDTO updated = bookEditionService.update(id, bookEditionDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookEditionService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}