package com.medicalrecords.config;

import com.medicalrecords.entity.User;
import com.medicalrecords.entity.enums.RoleType;
import com.medicalrecords.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Създава начален администратор.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer
        implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername(
                "admin").isEmpty()) {

            User admin = new User();

            admin.setUsername("admin");

            admin.setPassword(
                    passwordEncoder.encode(
                            "admin123"
                    )
            );

            admin.setRole(
                    RoleType.ROLE_ADMIN
            );

            admin.setEnabled(true);

            userRepository.save(admin);
        }
    }
}