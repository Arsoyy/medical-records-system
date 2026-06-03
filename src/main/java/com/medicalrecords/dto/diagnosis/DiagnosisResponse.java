package com.medicalrecords.dto.diagnosis;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO за връщане на диагноза.
 */
@Getter
@Setter
public class DiagnosisResponse {

    /**
     * ID на диагнозата.
     */
    private Long id;

    /**
     * Код на диагнозата.
     */
    private String code;

    /**
     * Име на диагнозата.
     */
    private String name;

    /**
     * Описание на диагнозата.
     */
    private String description;
}