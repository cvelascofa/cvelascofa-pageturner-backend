package uoc.tfg.cvelascofa.pageturner_backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
