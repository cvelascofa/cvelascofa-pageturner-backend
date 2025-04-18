package uoc.tfg.cvelascofa.pageturner_backend.entity.book;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.entity.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.entity.user.User;

@Data
@Entity
@Table(name = "reading_progress")
@EqualsAndHashCode(callSuper = true)
public class ReadingProgress extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_edition_id", nullable = false)
    private BookEdition bookEdition;

    @Column(name = "pages_read", nullable = false)
    private int pagesRead;

    @ManyToOne
    @JoinColumn(name = "reading_status_fk", nullable = false)
    private ReadingStatus readingStatus;

}