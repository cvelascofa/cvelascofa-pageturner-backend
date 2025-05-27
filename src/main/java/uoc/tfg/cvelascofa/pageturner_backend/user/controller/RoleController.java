package uoc.tfg.cvelascofa.pageturner_backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.RoleDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/name")
    public ResponseEntity<String> getRoleNameById(@RequestParam Long id) {
        String roleName = roleService.getRoleNameById(id);
        return ResponseEntity.ok(roleName);
    }
}
