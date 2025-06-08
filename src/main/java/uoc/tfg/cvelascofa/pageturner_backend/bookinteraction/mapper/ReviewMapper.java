package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.ReviewDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity.Review;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "id.userId", target = "userId")
    @Mapping(source = "id.bookId", target = "bookId")
    ReviewDTO toDTO(Review review);

    @Mapping(source = "userId", target = "id.userId")
    @Mapping(source = "bookId", target = "id.bookId")
    Review toEntity(ReviewDTO reviewDTO);

    List<ReviewDTO> toDTOList(List<Review> reviews);

}