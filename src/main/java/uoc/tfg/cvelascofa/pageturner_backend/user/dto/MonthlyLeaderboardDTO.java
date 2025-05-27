package uoc.tfg.cvelascofa.pageturner_backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyLeaderboardDTO {

    private Long userId;
    private Integer month;
    private Integer year;
    private Integer pagesRead;
    private Integer rankingPosition;

}