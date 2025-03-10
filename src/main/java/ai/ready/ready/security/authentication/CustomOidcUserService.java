package ai.ready.ready.security.authentication;

import ai.ready.ready.security.authentication.dto.Oauth2UserInfoDto;
import ai.ready.ready.user.User;
import ai.ready.ready.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    private final UserRepository userRepository;

    public OidcUser loadUser(OidcUserRequest userRequest){
        OidcUser oidcUser = new OidcUserService().loadUser(userRequest);

        Oauth2UserInfoDto userInfoDto = Oauth2UserInfoDto.builder()
                .email(oidcUser.getEmail())
                .name(oidcUser.getFullName())
                .picture(oidcUser.getPicture())
                .build();

        userRepository.findByEmail(userInfoDto.getEmail())
                .map(existingUser -> updateExistingUser(existingUser, userInfoDto))
                .orElseGet(() -> registerNewUser(userInfoDto));

        return oidcUser;
    }

    private User registerNewUser(Oauth2UserInfoDto userInfoDto) {
        User user = User.builder()
                .email(userInfoDto.getEmail())
                .username(userInfoDto.getName())
                .imageUrl(userInfoDto.getPicture())
                .build();

        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, Oauth2UserInfoDto userInfoDto) {
        existingUser.setEmail(userInfoDto.getEmail());
        existingUser.setUsername(userInfoDto.getName());
        existingUser.setImageUrl(userInfoDto.getPicture());

        return userRepository.save(existingUser);
    }



}
