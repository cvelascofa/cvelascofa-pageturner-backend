package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReadingProgressDTO {

    private Long id;
    private Long userId;
    private Long bookId;
    private String readingStatus;
    private Integer pagesRead;
    private LocalDateTime progressDate;

}