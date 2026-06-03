package com.medicalrecords.dto.report;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO за финансови справки.
 */
@Getter
@Setter
public class RevenueResponse {

    private BigDecimal totalAmount;
}