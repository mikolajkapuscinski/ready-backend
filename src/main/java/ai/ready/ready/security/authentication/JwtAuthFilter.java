package ai.ready.ready.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;

public class JwtAuthFilter extends BasicAuthenticationFilter {

    private static final String COOKIE_NAME = "token";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final UserDetailsService userDetailsService;
    private final String secret;

    public JwtAuthFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, String secret) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication;
        try {
            authentication = getAuthentication(request);
        } catch (TokenExpiredException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"JWT Token has expired\"}");
            response.getWriter().flush();
            return;
        }
        if (authentication == null) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        String token = Arrays.stream(request.getCookies())
                .filter((c) -> c.getName().equals(COOKIE_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
        if (token != null) {
            String username = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
        }
        return null;
    }
}
