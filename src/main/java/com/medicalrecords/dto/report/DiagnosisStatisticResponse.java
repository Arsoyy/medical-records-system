package com.medicalrecords.dto.report;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO за статистика на диагноза.
 */
@Getter
@Setter
public class DiagnosisStatisticResponse {

    private String diagnosisName;

    private Long occurrenceCount;
}