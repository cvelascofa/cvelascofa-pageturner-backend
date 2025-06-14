package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.BaseEntity;

import java.util.Set;

@Data
@Entity
@Table(name = "genres")
@EqualsAndHashCode(callSuper = true)
public class Genre extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Book> books;

}