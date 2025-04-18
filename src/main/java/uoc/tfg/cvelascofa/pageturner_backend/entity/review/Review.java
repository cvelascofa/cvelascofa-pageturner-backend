package uoc.tfg.cvelascofa.pageturner_backend.entity.review;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.entity.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.entity.book.Book;
import uoc.tfg.cvelascofa.pageturner_backend.entity.user.User;

@Data
@Entity
@Table(name = "reviews")
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private Integer rating;

}
