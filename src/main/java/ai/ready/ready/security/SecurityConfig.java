package ai.ready.ready.security;

import ai.ready.ready.security.authentication.JsonAuthFilter;
import ai.ready.ready.security.authentication.RestAuthFailureHandler;
import ai.ready.ready.security.authentication.RestAuthSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final RestAuthSuccessHandler successHandler;
    private final RestAuthFailureHandler failureHandler;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                     auth ->{
                         auth.requestMatchers(
                                 "/v3/**",
                                 "/swagger-ui/**",
                                 "/register",
                                 "/login"
                                 ).permitAll();
                         auth.anyRequest().authenticated();
                     }
                )
                .addFilter(jsonAuthFilter())
                .authenticationManager(authenticationManager())
                .build();
    }

    public JsonAuthFilter jsonAuthFilter() {
        JsonAuthFilter filter = new JsonAuthFilter(objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        return filter;
    }

    public AuthenticationManager authenticationManager(){
        return new ProviderManager(authProvider());
    }

    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
