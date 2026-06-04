package com.medicalrecords.service;

import com.medicalrecords.dto.patient.PatientCreateRequest;
import com.medicalrecords.dto.patient.PatientResponse;
import com.medicalrecords.dto.patient.PatientUpdateRequest;

import java.util.List;

/**
 * Интерфейс за работа с пациенти.
 */
public interface PatientService {

    /**
     * Създава пациент.
     */
    PatientResponse createPatient(
            PatientCreateRequest request
    );

    /**
     * Връща всички пациенти.
     */
    List<PatientResponse> getAllPatients();

    /**
     * Връща пациент по ID.
     */
    PatientResponse getPatientById(Long id);

    /**
     * Обновява пациент.
     */
    PatientResponse updatePatient(
            Long id,
            PatientUpdateRequest request
    );

    /**
     * Изтрива пациент.
     */
    void deletePatient(Long id);

    List<PatientResponse> getMyPatients();
}