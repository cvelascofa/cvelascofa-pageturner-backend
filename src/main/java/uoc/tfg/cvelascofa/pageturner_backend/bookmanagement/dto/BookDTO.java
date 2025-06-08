package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String description;
    private String coverImage;
    private String isbn;
    private LocalDate publicationDate;
    private Integer pages;

    private GenreDTO genre;
    private LanguageDTO language;
    private AuthorDTO author;
    private PublisherDTO publisher;
    private EditionTypeDTO editionType;

}
