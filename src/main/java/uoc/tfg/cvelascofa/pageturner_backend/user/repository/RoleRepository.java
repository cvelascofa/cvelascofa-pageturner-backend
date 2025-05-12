package uoc.tfg.cvelascofa.pageturner_backend.user.repository;

import uoc.tfg.cvelascofa.pageturner_backend.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
