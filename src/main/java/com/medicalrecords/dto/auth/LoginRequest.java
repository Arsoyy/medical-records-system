package com.medicalrecords.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO за login заявка.
 */
@Getter
@Setter
public class LoginRequest {

    /**
     * Username на потребителя.
     */
    @NotBlank(message = "Username е задължителен.")
    private String username;

    /**
     * Парола на потребителя.
     */
    @NotBlank(message = "Паролата е задължителна.")
    private String password;
}