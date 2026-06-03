package com.medicalrecords.dto.examination;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO за редактиране на преглед.
 */
@Getter
@Setter
public class ExaminationUpdateRequest {

    /**
     * Нова диагноза.
     */
    @NotNull(message = "Диагнозата е задължителна.")
    private Long diagnosisId;

    /**
     * Ново лечение.
     */
    @NotBlank(message = "Лечението е задължително.")
    private String treatment;

    /**
     * Нова цена.
     */
    @NotNull(message = "Цената е задължителна.")
    @DecimalMin(value = "0.00",
            message = "Цената не може да бъде отрицателна.")
    private BigDecimal price;
}