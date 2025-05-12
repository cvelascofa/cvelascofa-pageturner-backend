package uoc.tfg.cvelascofa.pageturner_backend.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.RoleDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.Role;
import uoc.tfg.cvelascofa.pageturner_backend.user.mapper.RoleMapper;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.RoleRepository;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.RoleService;

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
}