package ai.ready.ready.security.authentication;

import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import ai.ready.ready.user.User;
import ai.ready.ready.user.UserRepository;
import ai.ready.ready.user.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new UserDetailsModel(user, getAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> getAuthorities(List<Role> roles) {
        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }
}
