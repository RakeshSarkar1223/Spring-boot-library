package com.library.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ❌ Disable CSRF (needed for REST APIs)
                .csrf(csrf -> csrf.disable())

                // 🔐 Authorization rules
                .authorizeHttpRequests(auth -> auth

                        // ✅ Swagger & OpenAPI (PUBLIC)
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // 🔐 Protect APIs
                        .requestMatchers("/api/**").authenticated()

                        // ✅ Allow everything else
                        .anyRequest().permitAll()
                )

                // 🚫 No sessions (JWT / stateless)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
