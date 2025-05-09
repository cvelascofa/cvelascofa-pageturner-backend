package uoc.tfg.cvelascofa.pageturner_backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserRepository;

@Component
public class LibraryUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // No se puede cambiar el naming, pero realmente lo estamos buscando por email.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(LibraryUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
}
