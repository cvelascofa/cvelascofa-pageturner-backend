package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> getAllRoles();
    String getRoleNameById(Long id);

}