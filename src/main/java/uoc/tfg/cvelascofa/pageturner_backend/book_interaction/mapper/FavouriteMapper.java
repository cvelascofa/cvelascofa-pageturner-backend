package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.FavouriteDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.Favourite;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavouriteMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    FavouriteDTO toDTO(Favourite favourite);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    Favourite toEntity(FavouriteDTO favouriteDTO);

    List<FavouriteDTO> toDTOList(List<Favourite> favourites);

}
