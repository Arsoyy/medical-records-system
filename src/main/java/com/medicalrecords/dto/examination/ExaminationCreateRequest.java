package com.medicalrecords.dto.examination;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO за създаване на преглед.
 */
@Getter
@Setter
public class ExaminationCreateRequest {

    /**
     * ID на лекаря.
     */
    @NotNull(message = "Лекарят е задължителен.")
    private Long doctorId;

    /**
     * ID на пациента.
     */
    @NotNull(message = "Пациентът е задължителен.")
    private Long patientId;

    /**
     * ID на диагнозата.
     */
    @NotNull(message = "Диагнозата е задължителна.")
    private Long diagnosisId;

    /**
     * Назначено лечение.
     */
    @NotBlank(message = "Лечението е задължително.")
    private String treatment;

    /**
     * Цена на прегледа.
     */
    @NotNull(message = "Цената е задължителна.")
    @DecimalMin(value = "0.00",
            message = "Цената не може да бъде отрицателна.")
    private BigDecimal price;
}