package uoc.tfg.cvelascofa.pageturner_backend.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserStatisticsDTO {

    private Long userId;

    private int totalBooksRead;
    private int mostBooksInMonth;
    private int longestReadingStreak;
    private int currentReadingStreak;
    private int totalPagesRead;

    private int totalRatings;
    private double averageRating;

    private LocalDate lastReadingSession;

    private int booksLastYear;
    private int pagesLastYear;

    private String favoriteAuthor;
    private String mostReadGenre;

    private String mostActiveMonth;

    private LocalDate firstBookReadDate;
    private LocalDate latestBookReadDate;

}
