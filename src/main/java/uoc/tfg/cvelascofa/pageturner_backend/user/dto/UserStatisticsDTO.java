package uoc.tfg.cvelascofa.pageturner_backend.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserStatisticsDTO {

    private Long userId;
    private int totalBooksRead;
    private int rankingThisMonth;
    private int booksReadThisMonth;
    private int pagesReadThisMonth;
    private int totalPagesRead;
    private int totalRatings;
    private double averageRating;

}