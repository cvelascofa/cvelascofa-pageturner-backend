package uoc.tfg.cvelascofa.pageturner_backend.book.dto;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String description;
    private Integer publicationYear;
    private Long genreId;

}
