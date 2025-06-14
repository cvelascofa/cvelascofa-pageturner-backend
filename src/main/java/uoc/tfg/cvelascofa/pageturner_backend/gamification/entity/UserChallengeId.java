package uoc.tfg.cvelascofa.pageturner_backend.gamification.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChallengeId implements Serializable {

    private Long userId;
    private Long challengeId;

}
