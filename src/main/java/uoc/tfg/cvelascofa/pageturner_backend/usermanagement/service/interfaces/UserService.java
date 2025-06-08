package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserDisplayDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserCreateDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserCreateDTO save(UserCreateDTO userCreateDTO);
    List<UserCreateDTO> getAll();
    Optional<User> getById(Long id);
    UserDisplayDTO update(Long id, UserDisplayDTO userCreateDTO);
    User getByEmail(String email);
    Page<UserDisplayDTO> searchUsersPageable(String username, Pageable pageable);
    List<UserDisplayDTO> searchUsersByUsernameWithoutPagination(String username);
    Optional<UserDisplayDTO> getUserDisplayById(Long id);

}