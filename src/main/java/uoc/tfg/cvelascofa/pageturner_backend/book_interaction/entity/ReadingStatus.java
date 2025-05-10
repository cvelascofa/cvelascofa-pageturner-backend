package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.enums.ReadingStatusCode;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

@Data
@Entity
@Immutable
@Table(name = "reading_status")
@EqualsAndHashCode(callSuper = true)
public class ReadingStatus extends BaseEntity {

    @Column(name = "name", length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "code", length = 100)
    private ReadingStatusCode code;

}