package uoc.tfg.cvelascofa.pageturner_backend.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "user_statistics")
@Data
public class UserStatistics extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private int totalBooksRead;
    private int mostBooksInMonth;
    private int longestReadingStreak;
    private int currentReadingStreak;
    private int totalPagesRead;

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