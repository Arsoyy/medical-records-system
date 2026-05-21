package com.medicalrecords.dto.doctor;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO за връщане на информация за лекар.
 */
@Getter
@Setter
public class DoctorResponse {

    /**
     * ID на лекаря.
     */
    private Long id;

    /**
     * Идентификационен номер.
     */
    private String doctorIdentifier;

    /**
     * Име на лекаря.
     */
    private String fullName;

    /**
     * Специалност.
     */
    private String specialty;

    /**
     * Дали може да бъде личен лекар.
     */
    private boolean canBePersonalDoctor;
}