package uoc.tfg.cvelascofa.pageturner_backend.book.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

@Data
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

   @OneToMany(mappedBy = "book")
   private Set<BookEdition> bookEditions;

    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @ToString.Exclude
    private Set<Author> authors;

}