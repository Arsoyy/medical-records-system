package com.medicalrecords.dto.report;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO за кратка информация за пациент.
 */
@Getter
@Setter
public class PatientSimpleResponse {

    private Long id;

    private String fullName;

    private String egn;
}