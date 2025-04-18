package uoc.tfg.cvelascofa.pageturner_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "publishers")
public class Publisher extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String name;

    private String website;

    private String country;

    @OneToMany(mappedBy = "publisher")
    private Set<BookEdition> bookEditions;

}
