package ai.ready.ready.user;

import ai.ready.ready.book.dto.BookCardDto;
import ai.ready.ready.bookPossesion.BookPossessionService;
import ai.ready.ready.emailVerification.EmailVerificationService;
import ai.ready.ready.exceptions.UserAlreadyExistException;
import ai.ready.ready.exceptions.UserNotFoundException;
import ai.ready.ready.review.ReviewDTO;
import ai.ready.ready.review.ReviewDTOMapper;
import ai.ready.ready.review.ReviewService;
import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import ai.ready.ready.security.authentication.dto.RegistrationRequest;
import ai.ready.ready.user.dto.ProfileDto;
import ai.ready.ready.user.dto.ReadingStats;
import ai.ready.ready.user.dto.UpdateProfileRequest;
import ai.ready.ready.user.expPoints.Level;
import ai.ready.ready.user.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookPossessionService bookPossessionService;
    private final ReviewService reviewService;
    private final RoleRepository roleRepository;
    private final EmailVerificationService emailVerificationService;
    private final ReviewDTOMapper reviewDTOMapper;

    public void register(final RegistrationRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new UserAlreadyExistException(request.getEmail());

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .creationDate(new Date())
                .roles(Collections.singletonList(roleRepository.findByName("USER")))
                .build();

        userRepository.save(user);
        emailVerificationService.sendVerificationEmail(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(final Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public ProfileDto getProfile(UserDetailsModel userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        ReadingStats readingStats = gatherUserStats(user);
        List<BookCardDto> currentlyReading = bookPossessionService.getCurrentlyReadingByUserId(user.getId(), 10);
        List<BookCardDto> recentlyFinished = bookPossessionService.getRecentlyFinishedByUserId(user.getId(), 10);
        List<BookCardDto> toRead = bookPossessionService.getToReadByUserId(user.getId(), 10);
        List<ReviewDTO> reviews = reviewDTOMapper.toReviewDTOList(reviewService.getUserReviews(user.getId(), 5));
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

    public ProfileDto updateProfile(UserDetailsModel userDetails, UpdateProfileRequest updateProfileRequest) {
        User user = userRepository.findById(userDetails.getId()).orElseThrow(UserNotFoundException::new);
        user.setUsername(updateProfileRequest.username());
        user.setAboutMe(updateProfileRequest.aboutMe());
        user.setImageUrl(updateProfileRequest.imageUrl());
        userRepository.save(user);
        return getProfile(userDetails);
    }
}
 