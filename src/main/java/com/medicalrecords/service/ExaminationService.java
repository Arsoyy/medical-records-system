package com.medicalrecords.service;

import com.medicalrecords.dto.examination.*;

import java.util.List;

/**
 * Интерфейс за работа с прегледи.
 */
public interface ExaminationService {

    /**
     * Създава преглед.
     */
    ExaminationResponse createExamination(
            ExaminationCreateRequest request
    );

    List<ExaminationResponse> getMyDoctorExaminations();

    List<ExaminationResponse> getMyExaminations();

    /**
     * Връща всички прегледи.
     */
    List<ExaminationResponse> getAllExaminations();

    /**
     * Връща преглед по ID.
     */
    ExaminationResponse getExaminationById(Long id);

    /**
     * Обновява преглед.
     */
    ExaminationResponse updateExamination(
            Long id,
            ExaminationUpdateRequest request
    );

    /**
     * Изтрива преглед.
     */
    void deleteExamination(Long id);

    ExaminationResponse createExaminationAsDoctor(
            ExaminationCreateRequest request
    );
}