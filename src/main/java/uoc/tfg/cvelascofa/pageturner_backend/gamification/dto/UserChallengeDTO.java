package uoc.tfg.cvelascofa.pageturner_backend.gamification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChallengeDTO {

    private Long userId;
    private Long challengeId;
    private LocalDateTime completedAt;

}