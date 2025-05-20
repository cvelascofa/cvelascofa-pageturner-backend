package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.FavouriteDTO;

import java.util.List;

public interface FavouriteService {

    void create(FavouriteDTO favouriteDTO);
    void delete(Long id);
    List<FavouriteDTO> getFavouritesByUserId(Long userId);

}