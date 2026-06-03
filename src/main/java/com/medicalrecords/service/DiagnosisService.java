package com.medicalrecords.service;

import com.medicalrecords.dto.diagnosis.*;

import java.util.List;

/**
 * Интерфейс за работа с диагнози.
 */
public interface DiagnosisService {

    /**
     * Създава диагноза.
     */
    DiagnosisResponse createDiagnosis(
            DiagnosisCreateRequest request
    );

    /**
     * Връща всички диагнози.
     */
    List<DiagnosisResponse> getAllDiagnoses();

    /**
     * Връща диагноза по ID.
     */
    DiagnosisResponse getDiagnosisById(Long id);

    /**
     * Обновява диагноза.
     */
    DiagnosisResponse updateDiagnosis(
            Long id,
            DiagnosisUpdateRequest request
    );

    /**
     * Изтрива диагноза.
     */
    void deleteDiagnosis(Long id);
}