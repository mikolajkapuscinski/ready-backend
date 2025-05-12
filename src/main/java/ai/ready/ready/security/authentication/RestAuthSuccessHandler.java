package ai.ready.ready.security.authentication;

import ai.ready.ready.security.authentication.dto.UserDetailsModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

@Component
public class RestAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final long expirationTime;
    private final String secret;

    public RestAuthSuccessHandler(
            @Value("${jwt.expirationTime}") long expirationTime,
            @Value("${jwt.secret}") String secret
    ) {
        this.expirationTime = expirationTime;
        this.secret = secret;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String email;
        boolean isOidc = false;

        if(authentication.getPrincipal() instanceof UserDetailsModel) {
            email = ((UserDetailsModel) authentication.getPrincipal()).getUsername();
        }
        else if(authentication.getPrincipal() instanceof DefaultOidcUser) {
            email = ((DefaultOidcUser) authentication.getPrincipal()).getEmail();
            isOidc = true;
        }
        else {
            return;
        }

        String token = JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(secret));

        ResponseCookie jwtCookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofDays(1))
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        if(isOidc) {
            response.sendRedirect("http://localhost:5173/home");
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"Login successful\", \"redirectUrl\": \"http://localhost:5173/home\"}");
            response.getWriter().flush();
        }
    }
}
