package ai.ready.ready.user;

import ai.ready.ready.security.authentication.dto.LoginRequest;
import ai.ready.ready.security.authentication.dto.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "ddd";
    }
    @PostMapping("/login")
    public String login(@RequestBody final LoginRequest request)
    {
        return userService.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody final RegistrationRequest request) {
        userService.register(request);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

}
