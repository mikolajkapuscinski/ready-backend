package ai.ready.ready.user;

import ai.ready.ready.user.dto.LoginRequest;
import ai.ready.ready.user.dto.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String login(final LoginRequest loginRequest) {
        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        Authentication authResponse = authenticationManager.authenticate(authRequest);
        return authResponse.isAuthenticated() ? "Allow" : "Deny";
    }

    public void register(final RegistrationRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .creationDate(LocalDate.now())
                .active(true)
                .build();
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
}
