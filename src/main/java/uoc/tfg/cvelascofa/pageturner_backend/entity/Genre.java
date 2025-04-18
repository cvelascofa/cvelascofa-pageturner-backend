package uoc.tfg.cvelascofa.pageturner_backend.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Table(name = "genres")
@EqualsAndHashCode(callSuper = true)
public class Genre extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Book> books;

}