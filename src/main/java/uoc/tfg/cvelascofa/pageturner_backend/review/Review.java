package uoc.tfg.cvelascofa.pageturner_backend.review;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

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