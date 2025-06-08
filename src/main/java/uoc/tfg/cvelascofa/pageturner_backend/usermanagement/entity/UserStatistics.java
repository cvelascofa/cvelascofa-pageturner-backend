package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.BaseEntity;

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
    private int rankingThisMonth;

    @Column(nullable = false)
    private int booksReadThisMonth;

    @Column(nullable = false)
    private int pagesReadThisMonth;

    @Column(nullable = false)
    private int totalPagesRead;

    @Column(nullable = false)
    private int totalRatings;

    @Column(nullable = false)
    private double averageRating;

}