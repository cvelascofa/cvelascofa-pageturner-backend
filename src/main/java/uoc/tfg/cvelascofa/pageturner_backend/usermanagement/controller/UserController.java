package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.exception.UserAlreadyExistsException;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserCreateDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserDisplayDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository.UserRepository;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public UserCreateDTO create(@RequestBody UserCreateDTO userCreateDTO) {
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new UserAlreadyExistsException("This email is already in use. Please log in or use a different email.");
        }
        if (userRepository.existsByUsername(userCreateDTO.getUsername())) {
            throw new UserAlreadyExistsException("This user is already in use. Please log in or use a different user.");
        }
        return userService.save(userCreateDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOpt = userService.getById(id);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<UserCreateDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        try {
            User user = userService.getByEmail(email);
            return ResponseEntity.ok(user);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("authentication.principal.id == #id or hasRole('ADMIN')")
    public ResponseEntity<UserDisplayDTO> update(@PathVariable Long id, @RequestBody UserDisplayDTO userDisplayDTO) {
        try {
            UserDisplayDTO updatedUser = userService.update(id, userDisplayDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserDisplayDTO>> searchUsersPageable(
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserDisplayDTO> users = userService.searchUsersPageable(username, pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search/friends/candidates")
    public List<UserDisplayDTO> searchUsersWithoutPagination(@RequestParam String username) {
        return userService.searchUsersByUsernameWithoutPagination(username);
    }

}