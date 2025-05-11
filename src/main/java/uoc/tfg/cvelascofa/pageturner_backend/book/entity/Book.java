package uoc.tfg.cvelascofa.pageturner_backend.book.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.Favourite;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

@Data
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "cover_image")
    private String coverImage;

    private String isbn;

    private Integer pages;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "edition_type_id")
    private EditionType editionType;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Favourite> favourites;

}