package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity;

import jakarta.persistence.*;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

@Data
@Entity
@Table(name = "user_favourite_books")
public class Favourite  extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

}
