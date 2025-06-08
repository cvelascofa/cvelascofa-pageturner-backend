package uoc.tfg.cvelascofa.pageturner_backend.gamification.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_challenges")
public class UserChallenge {

    @EmbeddedId
    private UserChallengeId id;

    @ManyToOne(optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @MapsId("challengeId")
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @Column(nullable = false)
    private int progress;

    @Column(nullable = false)
    private boolean completed;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}