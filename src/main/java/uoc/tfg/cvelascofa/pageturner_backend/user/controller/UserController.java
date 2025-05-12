package uoc.tfg.cvelascofa.pageturner_backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserCreateDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserDisplayDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserCreateDTO create(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.save(userCreateDTO);
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
    public ResponseEntity<UserDisplayDTO> update(@PathVariable Long id, @RequestBody UserCreateDTO userCreateDTO) {
        try {
            UserDisplayDTO updatedUser = userService.update(id, userCreateDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserDisplayDTO>> searchUsersPageable(
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDisplayDTO> users = userService.searchUsersPageable(username, pageable);
        return ResponseEntity.ok(users);

    }


}
