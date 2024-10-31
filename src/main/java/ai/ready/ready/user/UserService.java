package ai.ready.ready.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User login(final String email, final String password) {
        return null;
    }

    public User register(final User user){
        return null;
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
}
