package uoc.tfg.cvelascofa.pageturner_backend.book.dto;

import lombok.Data;

@Data
public class BookEditionDTO {

    private Long id;

    private Long bookId;
    private Long publisherId;
    private Long editionTypeId;
    private Long languageId;

    private String description;
    private String coverImage;
    private String isbn;
    private Boolean isMainEdition;
    private Integer pages;

}