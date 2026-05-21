package com.medicalrecords.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO, който връща JWT token
 * след успешен login.
 */
@Getter
@AllArgsConstructor
public class AuthResponse {

    /**
     * JWT token.
     */
    private String token;
}