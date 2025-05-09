package uoc.tfg.cvelascofa.pageturner_backend.jwt;

import lombok.Data;

@Data
public class JWTAuthenticationRequest {

    private String email;
    private String password;

}