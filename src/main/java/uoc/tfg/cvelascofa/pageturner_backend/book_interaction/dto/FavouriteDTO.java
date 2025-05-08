package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto;

import lombok.Data;

@Data
public class FavouriteDTO {

    private Long id;
    private Long userId;
    private Long bookId;

}
