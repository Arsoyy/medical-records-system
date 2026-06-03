package com.medicalrecords.dto.examination;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO за връщане на информация за преглед.
 */
@Getter
@Setter
public class ExaminationResponse {

    private Long id;

    private LocalDateTime examinationDate;

    private String doctorName;

    private String patientName;

    private String diagnosisName;

    private String treatment;

    private BigDecimal price;

    private String paymentType;
}