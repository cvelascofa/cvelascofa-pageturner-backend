package uoc.tfg.cvelascofa.pageturner_backend.book.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private GenreDTO genre;
    private Set<AuthorDTO> authors;

}