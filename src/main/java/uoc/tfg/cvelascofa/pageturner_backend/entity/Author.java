package uoc.tfg.cvelascofa.pageturner_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "authors")
@Data
@EqualsAndHashCode(callSuper = true)
public class Author extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String website;

    @Column(name = "followers_count")
    private Integer followersCount;

    @ManyToMany(mappedBy = "authors")
    @ToString.Exclude
    private Set<Book> books;
}
