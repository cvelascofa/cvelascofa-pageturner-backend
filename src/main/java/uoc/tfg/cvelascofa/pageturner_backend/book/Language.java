package uoc.tfg.cvelascofa.pageturner_backend.book;

import jakarta.persistence.*;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

import java.util.Set;

@Data
@Entity
@Table(name = "languages")
public class Language extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 10)
    private String code;

    @OneToMany(mappedBy = "language")
    private Set<BookEdition> bookEditions;

}