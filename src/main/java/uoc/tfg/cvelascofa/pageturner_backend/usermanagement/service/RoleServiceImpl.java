package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.RoleDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.Role;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.mapper.RoleMapper;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository.RoleRepository;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String getRoleNameById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + id));
        return role.getName();
    }
}