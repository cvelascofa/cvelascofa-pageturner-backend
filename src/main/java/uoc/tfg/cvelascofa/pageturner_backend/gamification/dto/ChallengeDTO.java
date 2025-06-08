package uoc.tfg.cvelascofa.pageturner_backend.gamification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDTO {

    private Long id;
    private String name;
    private String description;
    private Integer targetQuantity;
    private String badgeUrl;
    private String category;

}