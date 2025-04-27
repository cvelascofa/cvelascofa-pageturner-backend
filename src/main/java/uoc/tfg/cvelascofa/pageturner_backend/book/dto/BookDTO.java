package uoc.tfg.cvelascofa.pageturner_backend.book.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String description;
    private Integer publicationYear;
    private GenreDTO genre;
    private Set<AuthorDTO> authors;
    private Set<BookEditionDTO> bookEditions;

}
