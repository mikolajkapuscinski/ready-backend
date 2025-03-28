package ai.ready.ready.security.authentication.dto;

import ai.ready.ready.security.authentication.PasswordMatches;
import ai.ready.ready.security.authentication.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordMatches
public class RegistrationRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String matchingPassword;
    @ValidEmail
    @NotNull
    private String email;
}
