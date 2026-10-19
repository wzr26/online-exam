
package com.wzr26.onlineexam.config;

import com.wzr26.onlineexam.service.JwtService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtService jwtService) {
        this.jwtAuthenticationFilter =
                new JwtAuthenticationFilter(jwtService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                        )
                        .accessDeniedHandler(
                                new org.springframework.security.web.access
                                .AccessDeniedHandlerImpl()
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/login"
                        ).permitAll()

                        .requestMatchers(
                                "/", "/login", "/register", "/error"
                        ).permitAll()

                        // Teacher-only: create questions
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/exams/*/questions",
                                "/api/exams/*/questions/**"
                        ).hasRole("TEACHER")

                        // Teacher-only: edit questions
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/exams/*/questions/**"
                        ).hasRole("TEACHER")

                        // Teacher-only: delete questions
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/exams/*/questions/**"
                        ).hasRole("TEACHER")

                        // All other endpoints require a valid login
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }
}
