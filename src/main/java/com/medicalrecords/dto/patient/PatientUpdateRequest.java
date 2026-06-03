package com.medicalrecords.dto.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO за редактиране на пациент.
 */
@Getter
@Setter
public class PatientUpdateRequest {

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
    @NotNull(message = "Личният лекар е задължителен.")
    private Long personalDoctorId;
}