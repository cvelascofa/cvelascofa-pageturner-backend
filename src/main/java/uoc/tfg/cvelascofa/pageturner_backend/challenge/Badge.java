package uoc.tfg.cvelascofa.pageturner_backend.challenge;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "badges")
@EqualsAndHashCode(callSuper = true)
public class Badge extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

}