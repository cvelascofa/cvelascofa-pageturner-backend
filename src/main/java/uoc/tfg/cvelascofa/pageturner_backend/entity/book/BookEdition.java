package uoc.tfg.cvelascofa.pageturner_backend.entity.book;

import jakarta.persistence.*;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.entity.BaseEntity;

@Data
@Entity
@Table(name = "book_editions")
public class BookEdition extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "edition_type_id", nullable = false)
    private EditionType editionType;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(name = "description")
    private String description;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "is_main_edition")
    private Boolean isMainEdition = false;

    @Column(name = "pages")
    private Integer pages;

}