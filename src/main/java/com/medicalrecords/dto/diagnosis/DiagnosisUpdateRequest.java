package com.medicalrecords.dto.diagnosis;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO за редактиране на диагноза.
 */
@Getter
@Setter
public class DiagnosisUpdateRequest {

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