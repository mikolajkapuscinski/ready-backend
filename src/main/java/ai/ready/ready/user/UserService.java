package ai.ready.ready.user;

import ai.ready.ready.book.dto.BookCardDto;
import ai.ready.ready.bookPossesion.BookPossessionService;
import ai.ready.ready.exceptions.UserNotFoundException;
import ai.ready.ready.review.Review;
import ai.ready.ready.review.ReviewService;
import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import ai.ready.ready.security.authentication.dto.RegistrationRequest;
import ai.ready.ready.user.dto.ProfileDto;
import ai.ready.ready.user.dto.ReadingStats;
import ai.ready.ready.user.dto.UpdateProfileRequest;
import ai.ready.ready.user.expPoints.Level;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookPossessionService bookPossessionService;
    private final ReviewService reviewService;

    public void register(final RegistrationRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .creationDate(new Date())
                .active(true)
                .build();
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public ProfileDto getProfile(UserDetailsModel userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        ReadingStats readingStats = gatherUserStats(user);
        List<BookCardDto> currentlyReading = bookPossessionService.getCurrentlyReadingByUserId(user.getId(), 10);
        List<BookCardDto> recentlyFinished = bookPossessionService.getRecentlyFinishedByUserId(user.getId(), 10);
        List<BookCardDto> toRead = bookPossessionService.getToReadByUserId(user.getId(), 10);
        List<Review> reviews = reviewService.getUserReviews(user.getId(), 5);
        return new ProfileDto(
                user.getUsername(),
                user.getImageUrl(),
                user.getCreationDate(),
                currentlyReading,
                recentlyFinished,
                toRead,
                readingStats,
                user.getBadges(),
                reviews,
                user.getAboutMe()
        );
    }

    private ReadingStats gatherUserStats(User user) {
        Level level = Level.getUserLevel(user);

        return new ReadingStats(
                level.level(),
                level.progression(),
                bookPossessionService.getNumberOfFinishedBooks(user.getId()),
                bookPossessionService.getNumberOfFinishedPages(user.getId()),
                getUserAverageTimePerWeek(user.getId())
        );
    }

    private Integer getUserAverageTimePerWeek(Long userId) {
        return 0; //TODO
    }

    public void updateProfile(UserDetailsModel userDetails, UpdateProfileRequest updateProfileRequest) {
        User user = userRepository.findById(userDetails.getId()).orElseThrow(UserNotFoundException::new);
        user.setUsername(updateProfileRequest.username());
        user.setAboutMe(updateProfileRequest.aboutMe());
        user.setImageUrl(updateProfileRequest.imageUrl());
        userRepository.save(user);
    }
}
 