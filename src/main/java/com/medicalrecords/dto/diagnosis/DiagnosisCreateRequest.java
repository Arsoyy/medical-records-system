package com.medicalrecords.dto.diagnosis;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO за създаване на диагноза.
 */
@Getter
@Setter
public class DiagnosisCreateRequest {

    /**
     * Уникален код на диагнозата.
     */
    @NotBlank(message = "Кодът е задължителен.")
    private String code;

    /**
     * Име на диагнозата.
     */
    @NotBlank(message = "Името е задължително.")
    private String name;

    /**
     * Описание на диагнозата.
     */
    private String description;
}