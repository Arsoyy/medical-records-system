package com.medicalrecords.dto.report;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO за приходи по лекар.
 */
@Getter
@Setter
public class DoctorRevenueResponse {

    private String doctorName;

    private BigDecimal totalAmount;
}