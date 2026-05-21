package com.medicalrecords.security;

import com.medicalrecords.entity.User;
import com.medicalrecords.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Service клас, който зарежда потребителите
 * от базата данни за Spring Security.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Зарежда потребител по username.
     *
     * @param username username
     * @return UserDetails
     * @throws UsernameNotFoundException ако няма такъв потребител
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Потребителят не е намерен.")
                );

        return new CustomUserDetails(user);
    }
}