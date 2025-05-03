package uoc.tfg.cvelascofa.pageturner_backend.book.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.user.User;

@Data
@Entity
@Table(name = "reading_progress")
@EqualsAndHashCode(callSuper = true)
public class ReadingProgress extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "pages_read", nullable = false)
    private int pagesRead;

    @ManyToOne
    @JoinColumn(name = "reading_status_fk", nullable = false)
    private ReadingStatus readingStatus;

}