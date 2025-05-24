package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity;

import jakarta.persistence.*;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.enums.ReadingStatus;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reading_progress")
public class ReadingProgress extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "reading_status", nullable = false)
    private ReadingStatus readingStatus;

    @Column(name = "pages_read")
    private Integer pagesRead;

    @Column(name = "progress_date")
    private LocalDateTime progressDate;

}