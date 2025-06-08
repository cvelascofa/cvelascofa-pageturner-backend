package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.FavouriteDTO;

import java.util.List;

public interface FavouriteService {

    void create(FavouriteDTO favouriteDTO);
    void delete(Long id);
    List<FavouriteDTO> getFavouritesByUserId(Long userId);

}