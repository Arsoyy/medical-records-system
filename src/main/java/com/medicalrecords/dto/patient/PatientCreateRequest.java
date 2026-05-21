package com.medicalrecords.dto.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO за създаване на пациент.
 */
@Getter
@Setter
public class PatientCreateRequest {

    /**
     * Име на пациента.
     */
    @NotBlank(message = "Името е задължително.")
    private String fullName;

    /**
     * ЕГН на пациента.
     */
    @Pattern(
            regexp = "\\d{10}",
            message = "ЕГН трябва да съдържа точно 10 цифри."
    )
    private String egn;

    /**
     * Здравноосигурителен статус.
     */
    private boolean insured;

    /**
     * ID на личния лекар.
     */
    private Long personalDoctorId;

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