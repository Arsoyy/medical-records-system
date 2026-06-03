package com.medicalrecords.dto.patient;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO за връщане на информация за пациент.
 */
@Getter
@Setter
public class PatientResponse {

    /**
     * ID на пациента.
     */
    private Long id;

    /**
     * Име на пациента.
     */
    private String fullName;

    /**
     * ЕГН на пациента.
     */
    private String egn;

    /**
     * Здравноосигурителен статус.
     */
    private boolean insured;

    /**
     * Име на личния лекар.
     */
    private String personalDoctorName;
}