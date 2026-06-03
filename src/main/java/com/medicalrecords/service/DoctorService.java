package com.medicalrecords.service;

import com.medicalrecords.dto.doctor.*;

import java.util.List;

/**
 * Интерфейс за работа с лекари.
 */
public interface DoctorService {

    /**
     * Създава лекар.
     */
    DoctorResponse createDoctor(
            DoctorCreateRequest request
    );

    /**
     * Връща всички лекари.
     */
    List<DoctorResponse> getAllDoctors();

    /**
     * Връща лекар по ID.
     */
    DoctorResponse getDoctorById(Long id);

    /**
     * Обновява лекар.
     */
    DoctorResponse updateDoctor(
            Long id,
            DoctorUpdateRequest request
    );

    /**
     * Изтрива лекар.
     */
    void deleteDoctor(Long id);
}