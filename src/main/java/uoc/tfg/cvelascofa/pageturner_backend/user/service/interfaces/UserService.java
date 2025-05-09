package uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO save(UserDTO userDTO);
    //boolean existsByUsername(String username);
    //boolean existsByEmail(String email);
    List<UserDTO> getAll();
    Optional<User> getById(Long id);
    //UserDTO userByUsername(String username);
    //void deleteUser(Long id);
    //UserDTO updateUser(UserDTO userDTO);
    User getByEmail(String email);
    //List<UserDTO> getPaginatedUsers(int page, int size);
}
