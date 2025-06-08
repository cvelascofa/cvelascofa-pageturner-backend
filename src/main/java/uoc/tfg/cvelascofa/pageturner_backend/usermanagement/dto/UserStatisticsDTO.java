package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto;

import lombok.Data;

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