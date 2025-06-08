package uoc.tfg.cvelascofa.pageturner_backend.gamification.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.BaseEntity;

@Data
@Entity
@Table(name = "challenges")
@EqualsAndHashCode(callSuper = true)
public class Challenge extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "target_quantity", nullable = false)
    private Integer targetQuantity;

    @Column(name = "badge_url", nullable = false)
    private String badgeUrl;

    @Column(name = "category", nullable = false)
    private String category;

}