package uoc.tfg.cvelascofa.pageturner_backend.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.EditionTypeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.EditionTypeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/edition-types")
public class EditionTypeController {

    @Autowired
    private EditionTypeService editionTypeService;

    @PostMapping
    public ResponseEntity<EditionTypeDTO> create(@RequestBody EditionTypeDTO editionTypeDTO) {
        EditionTypeDTO created = editionTypeService.create(editionTypeDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EditionTypeDTO>> getAll() {
        List<EditionTypeDTO> editionTypes = editionTypeService.getAll();
        return new ResponseEntity<>(editionTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditionTypeDTO> getById(@PathVariable Long id) {
        Optional<EditionTypeDTO> editionTypeDTO = editionTypeService.getById(id);
        return editionTypeDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditionTypeDTO> update(@PathVariable Long id, @RequestBody EditionTypeDTO editionTypeDTO) {
        Optional<EditionTypeDTO> updated = editionTypeService.update(id, editionTypeDTO);
        return updated.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<EditionTypeDTO> editionType = editionTypeService.getById(id);
        if (editionType.isPresent()) {
            editionTypeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<EditionTypeDTO>> searchEditionTypesPageable(
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
        Page<EditionTypeDTO> editionTypes = editionTypeService.searchEditionTypesPageable(name, pageable);
        return ResponseEntity.ok(editionTypes);
    }
}