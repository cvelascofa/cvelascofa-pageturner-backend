package uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);
    //boolean existsByUsername(String username);
    //boolean existsByEmail(String email);
    List<UserDTO> getAll();
    //UserDTO userPerId(Long id);
    //UserDTO userByUsername(String username);
    //void deleteUser(Long id);
    //UserDTO updateUser(UserDTO userDTO);
    //UserDTO getUser(String email);
    //List<UserDTO> getPaginatedUsers(int page, int size);
}
