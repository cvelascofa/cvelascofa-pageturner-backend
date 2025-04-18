package uoc.tfg.cvelascofa.pageturner_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "edition_types")
public class EditionType extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String typeName;

    @OneToMany(mappedBy = "editionType")
    private Set<BookEdition> bookEditions;

}
