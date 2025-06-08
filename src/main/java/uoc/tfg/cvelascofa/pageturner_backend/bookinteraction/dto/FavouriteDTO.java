package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto;

import lombok.Data;

@Data
public class FavouriteDTO {

    private Long id;
    private Long userId;
    private Long bookId;

}
