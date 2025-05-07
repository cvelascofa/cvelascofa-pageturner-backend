package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.FavouriteDTO;

public interface FavouriteService {

    void addFavourite(FavouriteDTO favouriteDTO);
    void removeFavourite(FavouriteDTO favouriteDTO);

}