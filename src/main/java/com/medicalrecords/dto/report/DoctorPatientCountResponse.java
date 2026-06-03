package com.medicalrecords.dto.report;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO за брой пациенти към лекар.
 */
@Getter
@Setter
public class DoctorPatientCountResponse {

    private String doctorName;

    private Long patientCount;
}