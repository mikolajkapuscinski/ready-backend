package ai.ready.ready.security.authentication;

import ai.ready.ready.emailVerification.EmailVerificationService;
import ai.ready.ready.security.authentication.dto.LoginRequest;
import ai.ready.ready.security.authentication.dto.RegistrationRequest;
import ai.ready.ready.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping("/login")
    public void login(@RequestBody final LoginRequest request)
    {
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid final RegistrationRequest request) {
        userService.register(request);
    }

    @GetMapping("/registrationConfirm")
    public void registrationConfirm(@RequestParam final String token) {
        emailVerificationService.confirmEmail(token);
    }
}
