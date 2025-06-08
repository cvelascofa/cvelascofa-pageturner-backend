package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto;

import lombok.Data;

@Data
public class ReviewDTO {

    private Long userId;
    private Long bookId;
    private int rating;
    private String comment;

}