package uoc.tfg.cvelascofa.pageturner_backend.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.UserServiceImpl;


public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;
    private UserServiceImpl userService;

    public CustomUserDetails(User user) {
        email = user.getEmail();
        password = user.getPassword();
        authorities = Arrays.stream(user.getRole().getName()
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        id = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUserToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((org.springframework.security.core.userdetails.UserDetails)auth.getPrincipal()).getUsername();
        User user = userService.getByEmail(username);
        return user;
    }

    public Long getId() {
        return id;
    }
}
