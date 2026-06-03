package com.medicalrecords.dto.report;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO за брой прегледи на лекар.
 */
@Getter
@Setter
public class DoctorVisitCountResponse {

    private String doctorName;

    private Long visitCount;
}