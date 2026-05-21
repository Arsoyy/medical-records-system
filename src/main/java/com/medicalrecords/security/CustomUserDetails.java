package com.medicalrecords.security;

import com.medicalrecords.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Custom implementation на UserDetails.
 *
 * Spring Security използва този клас,
 * за да работи с нашия User entity.
 */
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    /**
     * Връща ролята на потребителя.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(
                new SimpleGrantedAuthority(user.getRole().name())
        );
    }

    /**
     * Връща криптираната парола.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Връща username.
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Показва дали акаунтът е активен.
     */
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}