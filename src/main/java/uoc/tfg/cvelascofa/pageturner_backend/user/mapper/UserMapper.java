package uoc.tfg.cvelascofa.pageturner_backend.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role", target = "role")
    UserDTO toDTO(User user);

    @Mapping(source = "role", target = "role")
    User toEntity(UserDTO userDTO);

    List<UserDTO> toDTOList(List<User> users);
}
