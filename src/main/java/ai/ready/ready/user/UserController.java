package ai.ready.ready.user;

import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import ai.ready.ready.user.dto.ProfileDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/profile")
    public ProfileDto getProfile(@AuthenticationPrincipal UserDetailsModel user) {
        return userService.getProfile();
    }
}
