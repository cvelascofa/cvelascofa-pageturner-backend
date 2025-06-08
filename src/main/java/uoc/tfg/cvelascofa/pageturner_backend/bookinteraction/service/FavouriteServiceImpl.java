package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.FavouriteDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity.Favourite;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.mapper.FavouriteMapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.repository.FavouriteRepository;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service.interfaces.FavouriteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final FavouriteMapper favouriteMapper;

    @Override
    public void create(FavouriteDTO favouriteDTO) {
        Favourite favourite = favouriteMapper.toEntity(favouriteDTO);
        favouriteRepository.save(favourite);
    }

    @Override
    public void delete(Long id) {
        favouriteRepository.deleteById(id);
    }

    public List<FavouriteDTO> getFavouritesByUserId(Long userId) {
        List<Favourite> favourites = favouriteRepository.findByUserId(userId);
        return favourites.stream()
                .map(favouriteMapper::toDTO)
                .collect(Collectors.toList());
    }
}