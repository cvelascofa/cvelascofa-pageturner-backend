package uoc.tfg.cvelascofa.pageturner_backend.book.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

@Data
@Entity
@Table(name = "reading_status")
@EqualsAndHashCode(callSuper = true)
public class ReadingStatus extends BaseEntity {

    @Column(name = "status_name", nullable = false, length = 100)
    private String statusName;

}