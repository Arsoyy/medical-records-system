package com.medicalrecords.dto.sickleave;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO за създаване на болничен лист.
 */
@Getter
@Setter
public class SickLeaveCreateRequest {

    /**
     * Начална дата на болничния лист.
     */
    @NotNull(message = "Началната дата е задължителна.")
    private LocalDate startDate;

    /**
     * Брой дни.
     */
    @NotNull(message = "Броят дни е задължителен.")
    @Min(value = 1,
            message = "Броят дни трябва да бъде поне 1.")
    private Integer days;

    /**
     * ID на прегледа.
     */
    @NotNull(message = "Прегледът е задължителен.")
    private Long examinationId;
}