package uoc.tfg.cvelascofa.pageturner_backend.challenge;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "challenges")
@EqualsAndHashCode(callSuper = true)
public class Challenge extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "target_books", nullable = false)
    private Integer targetBooks;

    @ManyToOne
    @JoinColumn(name = "challenge_status_id", nullable = false)
    private ChallengeStatus challengeStatus;

}