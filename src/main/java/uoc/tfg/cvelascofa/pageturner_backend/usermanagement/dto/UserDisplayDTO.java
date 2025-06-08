package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto;

import lombok.Data;

@Data
public class UserDisplayDTO {

    private Long id;
    private String username;
    private String email;
    private RoleDTO role;

}