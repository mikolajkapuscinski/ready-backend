package ai.ready.ready.user;

import ai.ready.ready.book.BookCardDto;
import ai.ready.ready.bookPossesion.BookPossessionService;
import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import ai.ready.ready.user.dto.ProfileDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookPossessionService bookPossessionService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/me")
    public ProfileDto getProfile(@AuthenticationPrincipal UserDetailsModel userDetails) {
        return userService.getProfile(userDetails);
    }

    @GetMapping("/me/currently_reading")
    public List<BookCardDto> getCurrentlyReading(@AuthenticationPrincipal UserDetailsModel userDetails) {
        return bookPossessionService.getCurrentlyReadingByUserId(userDetails.getId());
    }

    @GetMapping("/me/recently_finished")
    public List<BookCardDto> getRecentlyFinished(@AuthenticationPrincipal UserDetailsModel userDetails) {
        return bookPossessionService.getRecentlyFinishedByUserId(userDetails.getId());
    }

    @GetMapping("/me/to_read")
    public List<BookCardDto> getToRead(@AuthenticationPrincipal UserDetailsModel userDetails) {
        return bookPossessionService.getToReadByUserId(userDetails.getId());
    }

}
