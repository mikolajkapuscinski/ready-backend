package ai.ready.ready.user;

import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import ai.ready.ready.user.dto.ProfileDto;
import ai.ready.ready.user.dto.UpdateProfileRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/me")
    public ProfileDto getProfile(@AuthenticationPrincipal UserDetailsModel userDetails) {
        return userService.getProfile(userDetails);
    }

    @PatchMapping("/me")
    public ResponseEntity<ProfileDto> updateProfile(@AuthenticationPrincipal UserDetailsModel userDetails, UpdateProfileRequest updateProfileRequest){
        ProfileDto updatedProfile = userService.updateProfile(userDetails, updateProfileRequest);
        return ResponseEntity.accepted().body(updatedProfile);
    }

}
