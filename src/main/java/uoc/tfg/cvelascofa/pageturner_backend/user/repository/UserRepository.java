package uoc.tfg.cvelascofa.pageturner_backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
