package uoc.tfg.cvelascofa.pageturner_backend.challenge;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "user_challenges")
@EqualsAndHashCode(callSuper = true)
public class UserChallenge extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "user_challenge_status_id", nullable = false)
    private UserChallengeStatus userChallengeStatus;

    @Column(name = "completion_date")
    private LocalDate completionDate;

}