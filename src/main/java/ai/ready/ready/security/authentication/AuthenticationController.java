package ai.ready.ready.security.authentication;

import ai.ready.ready.security.authentication.dto.LoginRequest;
import ai.ready.ready.security.authentication.dto.RegistrationRequest;
import ai.ready.ready.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/login")
    public void login(@RequestBody final LoginRequest request)
    {
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid final RegistrationRequest request) {
        userService.register(request);
    }
}
