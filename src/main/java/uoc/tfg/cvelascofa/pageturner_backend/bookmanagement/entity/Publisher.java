package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.BaseEntity;

import java.util.Set;

    @Data
    @Entity
    @Table(name = "publishers")
    public class Publisher extends BaseEntity {

        @Column(nullable = false, length = 255)
        private String name;

        private String website;

        private String country;

        @OneToMany(mappedBy = "publisher")
        private Set<Book> books;

    }