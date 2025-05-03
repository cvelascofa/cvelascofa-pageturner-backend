package uoc.tfg.cvelascofa.pageturner_backend.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.user.Role;
import uoc.tfg.cvelascofa.pageturner_backend.user.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.mapper.UserMapper;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.RoleRepository;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userDTO.getRole() != null && userDTO.getRole().getId() != null) {
            Role role = roleRepository.findById(userDTO.getRole().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Role not found for ID: " + userDTO.getRole().getId()));
            user.setRole(role);
        }

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
