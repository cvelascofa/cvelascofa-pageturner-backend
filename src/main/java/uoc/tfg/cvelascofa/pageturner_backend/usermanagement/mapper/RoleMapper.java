package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.RoleDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.Role;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);
    List<RoleDTO> toDTOList(List<Role> roles);

}
