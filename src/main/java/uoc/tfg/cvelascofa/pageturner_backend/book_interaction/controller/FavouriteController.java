package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.FavouriteDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.FavouriteService;

@RestController
@RequestMapping("/favorites")
public class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @PostMapping
    public ResponseEntity<Void> addFavourite(@RequestBody FavouriteDTO favouriteDTO) {
        favouriteService.addFavourite(favouriteDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeFavourite(@RequestBody FavouriteDTO favouriteDTO) {
        favouriteService.removeFavourite(favouriteDTO);
        return ResponseEntity.ok().build();
    }

}