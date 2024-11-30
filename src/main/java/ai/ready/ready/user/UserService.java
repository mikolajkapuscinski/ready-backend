package ai.ready.ready.user;

import ai.ready.ready.book.Book;
import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import ai.ready.ready.security.authentication.dto.RegistrationRequest;
import ai.ready.ready.user.dto.ProfileDto;
import ai.ready.ready.user.dto.ReadingStats;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    public ProfileDto getProfile(UserDetailsModel userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        ReadingStats readingStats = gatherUserStats(user.getId()); //TODO
        List<Book> currentlyReading = List.of(); //TODO
        List<Book> recentlyFinished = List.of(); //TODO
        return new ProfileDto(
                user.getUsername(),
                user.getImage(),
                currentlyReading,
                recentlyFinished,
                readingStats);
    }

    ReadingStats gatherUserStats(Long userId) {
        return new ReadingStats(
                userRepository.findTotalPagesByUserId(userId),
                userRepository.findTotalBooksByUserId(userId)
        );
    }
}
