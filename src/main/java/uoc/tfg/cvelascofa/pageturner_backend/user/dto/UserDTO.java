package uoc.tfg.cvelascofa.pageturner_backend.user.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private RoleDTO role;

}
