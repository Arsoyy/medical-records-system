package com.medicalrecords.service;

import com.medicalrecords.dto.doctor.DoctorCreateRequest;
import com.medicalrecords.dto.doctor.DoctorResponse;

import java.util.List;

/**
 * Интерфейс, описващ бизнес логиката
 * за работа с лекари.
 */
public interface DoctorService {

    /**
     * Създава нов лекар.
     *
     * @param request входни данни
     * @return създаденият лекар
     */
    DoctorResponse createDoctor(DoctorCreateRequest request);

    /**
     * Връща всички лекари.
     *
     * @return списък с лекари
     */
    List<DoctorResponse> getAllDoctors();

    /**
     * Връща лекар по ID.
     *
     * @param id ID на лекаря
     * @return информация за лекаря
     */
    DoctorResponse getDoctorById(Long id);

    /**
     * Изтрива лекар.
     *
     * @param id ID на лекаря
     */
    void deleteDoctor(Long id);
}