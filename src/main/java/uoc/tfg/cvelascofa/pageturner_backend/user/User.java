package uoc.tfg.cvelascofa.pageturner_backend.user;

import java.util.Set;

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
    
    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}