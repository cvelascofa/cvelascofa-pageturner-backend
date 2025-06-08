package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserCreateDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserDisplayDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // UserCreateDTO
    UserCreateDTO toCreateDTO(User user);
    User toEntity(UserCreateDTO userCreateDTO);
    List<UserCreateDTO> toDTOList(List<User> users);

    // UserDisplayDTO
    UserDisplayDTO toDisplayDTO(User user);
    User toEntity(UserDisplayDTO dto);
    List<UserDisplayDTO> toDisplayDTOList(List<User> users);

}