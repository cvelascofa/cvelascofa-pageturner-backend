package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity;

import jakarta.persistence.*;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;

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
