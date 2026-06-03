package com.medicalrecords.dto.sickleave;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO за връщане на информация за болничен лист.
 */
@Getter
@Setter
public class SickLeaveResponse {

    private Long id;

    private LocalDate startDate;

    private Integer days;

    private String doctorName;

    private String patientName;

    private Long examinationId;
}