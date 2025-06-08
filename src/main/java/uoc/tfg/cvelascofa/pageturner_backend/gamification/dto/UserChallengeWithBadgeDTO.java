package uoc.tfg.cvelascofa.pageturner_backend.gamification.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserChallengeWithBadgeDTO {

    private Long challengeId;
    private String challengeName;
    private String badgeUrl;
    private boolean completed;
    private int progress;
    private LocalDateTime createdAt;

}
