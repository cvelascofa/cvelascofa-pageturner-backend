package uoc.tfg.cvelascofa.pageturner_backend.user.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.RoleDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.Role;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);
    List<RoleDTO> toDTOList(List<Role> roles);

}
