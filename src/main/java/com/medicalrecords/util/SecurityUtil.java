package com.medicalrecords.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Помощен клас за работа с логнатия потребител.
 */
public final class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * Връща username на логнатия потребител.
     */
    public static String getCurrentUsername() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        return authentication.getName();
    }
}