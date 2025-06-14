package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.exception.UserNotFoundException;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserCreateDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserDisplayDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.Role;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.mapper.UserMapper;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository.RoleRepository;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.UserService;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.UserStatisticsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserStatisticsService userStatisticsService;

    @Override
    public UserCreateDTO save(UserCreateDTO userCreateDTO) {
        User user = userMapper.toEntity(userCreateDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userCreateDTO.getRole() != null && userCreateDTO.getRole().getId() != null) {
            Role role = roleRepository.findByName(userCreateDTO.getRole().getName())
                    .orElseThrow(() -> new IllegalArgumentException("Role not found"));
            user.setRole(role);
        }

        User savedUser = userRepository.save(user);
        userStatisticsService.createStatistics(savedUser);
        return userMapper.toCreateDTO(savedUser);
    }

    @Override
    public List<UserCreateDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toCreateDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserDisplayDTO> getUserDisplayById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDisplayDTO);
    }

    @Override
    public UserDisplayDTO update(Long id, UserDisplayDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());

        if (userDTO.getRole() != null && userDTO.getRole().getId() != null) {
            Role role = roleRepository.findByName(userDTO.getRole().getName())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            existingUser.setRole(role);
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDisplayDTO(updatedUser);
    }

    @Override
    public Page<UserDisplayDTO> searchUsersPageable(String username, Pageable pageable) {
        Page<User> usersPage = userRepository.findByUsernameContainingIgnoreCase(username, pageable);
        return usersPage.map(userMapper::toDisplayDTO);
    }

    @Override
    public List<UserDisplayDTO> searchUsersByUsernameWithoutPagination(String username) {
        List<User> users = userRepository.findByUsernameContainingIgnoreCase(username);
        return users.stream()
                .map(userMapper::toDisplayDTO)
                .toList();
    }

}