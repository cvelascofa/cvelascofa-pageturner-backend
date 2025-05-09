package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.FavouriteDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.FavouriteService;

import java.util.List;

@RestController
@RequestMapping("/favourites")
public class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FavouriteDTO favouriteDTO) {
        favouriteService.create(favouriteDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        favouriteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FavouriteDTO>> getByUserId(@RequestParam Long userId) {
        List<FavouriteDTO> favourites = favouriteService.getFavouritesByUserId(userId);
        return ResponseEntity.ok(favourites);
    }
}