package com.medicalrecords.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO за създаване на лекар.
 */
@Getter
@Setter
public class DoctorCreateRequest {

    /**
     * Уникален идентификационен номер.
     */
    @NotBlank(message = "Идентификационният номер е задължителен.")
    private String doctorIdentifier;

    /**
     * Име на лекаря.
     */
    @NotBlank(message = "Името е задължително.")
    private String fullName;

    /**
     * Специалност.
     */
    @NotBlank(message = "Специалността е задължителна.")
    private String specialty;

    /**
     * Дали може да бъде личен лекар.
     */
    private boolean canBePersonalDoctor;

    /**
     * Username за login.
     */
    @NotBlank(message = "Username е задължителен.")
    private String username;

    /**
     * Парола.
     */
    @NotBlank(message = "Паролата е задължителна.")
    private String password;
}