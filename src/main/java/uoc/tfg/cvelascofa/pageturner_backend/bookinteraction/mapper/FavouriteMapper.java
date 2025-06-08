package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.FavouriteDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity.Favourite;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavouriteMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    FavouriteDTO toDTO(Favourite favourite);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    Favourite toEntity(FavouriteDTO favouriteDTO);

    List<FavouriteDTO> toDTOList(List<Favourite> favourites);

}
