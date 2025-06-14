package uoc.tfg.cvelascofa.pageturner_backend.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import uoc.tfg.cvelascofa.pageturner_backend.infrastructure.exception.UserNotFoundException;


@RestController
@RequestMapping("/login")
public class JWTController {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public Object getTokenForAuthenticatedUser(@RequestBody JWTAuthenticationRequest authRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            Map<String, Object> Response = new HashMap <String, Object> ();
            String token =  jwtService.generateToken(authRequest.getEmail());
            Response.put("token", token);

            return Response;
        }
        else {
            throw new UserNotFoundException("Invalid user credentials");
        }
    }
}