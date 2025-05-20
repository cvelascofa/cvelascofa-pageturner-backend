package uoc.tfg.cvelascofa.pageturner_backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserCreateDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserDisplayDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserCreateDTO create(@RequestBody UserCreateDTO userCreateDTO) {
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