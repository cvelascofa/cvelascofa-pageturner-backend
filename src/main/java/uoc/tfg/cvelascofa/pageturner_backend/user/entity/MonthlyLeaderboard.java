package uoc.tfg.cvelascofa.pageturner_backend.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "monthly_leaderboard")
public class MonthlyLeaderboard extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "pages_read", nullable = false)
    private Integer pagesRead;

    @Column(name = "ranking_position", nullable = false)
    private Integer rankingPosition;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
