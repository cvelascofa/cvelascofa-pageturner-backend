package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto;

import lombok.Data;

@Data
public class UserCreateDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private RoleDTO role;

}
