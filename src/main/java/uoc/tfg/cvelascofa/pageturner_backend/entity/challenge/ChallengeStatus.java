package uoc.tfg.cvelascofa.pageturner_backend.entity.challenge;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.entity.BaseEntity;

@Data
@Entity
@Table(name = "challenge_statuses")
@EqualsAndHashCode(callSuper = true)
public class ChallengeStatus extends BaseEntity {

    @Column(name = "status_name", nullable = false)
    private String statusName;

}
