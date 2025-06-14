package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.BaseEntity;

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

    @OneToMany(mappedBy = "author")
    private Set<Book> books;
}