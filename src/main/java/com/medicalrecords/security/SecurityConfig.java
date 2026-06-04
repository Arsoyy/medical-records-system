package com.medicalrecords.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация на Spring Security.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Конфигурира защитата на приложението.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                )

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.IF_REQUIRED
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/login",
                                "/css/**",
                                "/js/**"
                        )
                        .permitAll()

                        // Само ADMIN има достъп до лекарите
                        .requestMatchers("/api/doctors/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/examinations/my")
                        .hasRole("PATIENT")

                        .requestMatchers(
                                "/api/examinations/doctor/my"
                        )
                        .hasRole("DOCTOR")

                        .requestMatchers(
                                "/api/examinations/doctor"
                        )
                        .hasRole("DOCTOR")

                        .requestMatchers(
                                "/api/patients/my"
                        )
                        .hasRole("DOCTOR")

                        .requestMatchers(
                                "/api/sick-leaves/my"
                        )
                        .hasRole("DOCTOR")

                        // ADMIN и DOCTOR
                        .requestMatchers(
                                "/api/patients/**",
                                "/api/diagnoses/**",
                                "/api/examinations/**",
                                "/api/sick-leaves/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "DOCTOR"
                        )

                        .anyRequest()
                        .authenticated()
                );

        return http.build();
    }

    /**
     * Password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication manager.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {

        return configuration.getAuthenticationManager();
    }
}