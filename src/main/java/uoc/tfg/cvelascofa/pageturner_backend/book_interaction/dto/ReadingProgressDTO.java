package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto;


import lombok.Data;

@Data
public class ReadingProgressDTO {

    private Long userId;
    private Long bookId;
    //private String readingStatus;
    private Integer pagesRead;

}