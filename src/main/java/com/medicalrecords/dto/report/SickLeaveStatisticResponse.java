package com.medicalrecords.dto.report;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO за статистика на болнични.
 */
@Getter
@Setter
public class SickLeaveStatisticResponse {

    private String value;

    private Long count;
}