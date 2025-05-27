package uoc.tfg.cvelascofa.pageturner_backend.user.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private int totalBooksRead;

    @Column(nullable = false)
    private int mostBooksInMonth;

    @Column(nullable = false)
    private int longestReadingStreak;

    @Column(nullable = false)
    private int currentReadingStreak;

    @Column(nullable = false)
    private int totalPagesRead;

    @Column(nullable = false)
    private int totalRatings;

    @Column(nullable = false)
    private double averageRating;

    private LocalDate lastReadingSession;

    @Column(nullable = false)
    private int booksLastYear;

    @Column(nullable = false)
    private int pagesLastYear;

    private String favoriteAuthor;
    private String mostReadGenre;
    private String mostActiveMonth;

    private LocalDate firstBookReadDate;
    private LocalDate latestBookReadDate;

}