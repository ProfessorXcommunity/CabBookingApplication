package com.BookYourCab.CarBookingApp.Configs;

import com.BookYourCab.CarBookingApp.Security.JWTAuthFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {
    private final JWTAuthFilter jwtAuthFilter;
    private static final String[] PUBLIC_ROUTES = { "/**", "/auth/**", "/swagger-ui.html","/swagger-ui/**", "/v3/api-docs/**"};
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrfConfig ->csrfConfig.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(PUBLIC_ROUTES).permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        log.info("permitted the access points");
        return httpSecurity.build();
    }
}
