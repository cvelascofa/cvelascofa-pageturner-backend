package uoc.tfg.cvelascofa.pageturner_backend.user;

import jakarta.persistence.*;
import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}