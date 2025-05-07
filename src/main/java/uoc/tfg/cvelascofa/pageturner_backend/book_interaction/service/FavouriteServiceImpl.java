package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.FavouriteDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.Favourite;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.mapper.FavouriteMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository.FavouriteRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.FavouriteService;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final FavouriteMapper favouriteMapper;

    @Override
    public void addFavourite(FavouriteDTO favouriteDTO) {
        Favourite favourite = favouriteMapper.toEntity(favouriteDTO);
        favouriteRepository.save(favourite);
    }

    @Override
    public void removeFavourite(FavouriteDTO favouriteDTO) {
        Favourite favourite = favouriteMapper.toEntity(favouriteDTO);
        favouriteRepository.delete(favourite);
    }
}