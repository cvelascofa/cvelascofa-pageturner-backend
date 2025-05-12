package uoc.tfg.cvelascofa.pageturner_backend.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserCreateDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.UserDisplayDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // UserCreateDTO
    UserCreateDTO toCreateDTO(User user);
    User toEntity(UserCreateDTO userCreateDTO);
    List<UserCreateDTO> toDTOList(List<User> users);
    void updateUserFromDTO(UserCreateDTO userCreateDTO, @MappingTarget User user);


    // UserDisplayDTO
    UserDisplayDTO toDisplayDTO(User user);
    User toEntity(UserDisplayDTO dto);
    List<UserDisplayDTO> toDisplayDTOList(List<User> users);
    void updateUserFromDisplayDTO(UserDisplayDTO dto, @MappingTarget User user);

}
