package com.medicalrecords.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO за редакция на лекар.
 */
@Getter
@Setter
public class DoctorUpdateRequest {

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
}