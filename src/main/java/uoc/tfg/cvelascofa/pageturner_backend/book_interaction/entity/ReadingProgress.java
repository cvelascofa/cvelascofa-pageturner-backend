package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reading_progress")
public class ReadingProgress {

    @EmbeddedId
    private ReadingProgressId id;

    @ManyToOne(optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    //@ManyToOne
    //@JoinColumn(name = "reading_status_id")
    //private ReadingStatus readingStatus;

    @Column(name = "pages_read")
    private Integer pagesRead;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}