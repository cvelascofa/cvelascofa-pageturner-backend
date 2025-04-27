package uoc.tfg.cvelascofa.pageturner_backend.book.entity;

import jakarta.persistence.*;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

import java.util.Set;

@Data
@Entity
@Table(name = "edition_types")
public class EditionType extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "editionType")
    private Set<BookEdition> bookEditions;

}