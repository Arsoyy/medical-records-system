package com.medicalrecords.service.impl;

import com.medicalrecords.dto.examination.*;
import com.medicalrecords.entity.*;
import com.medicalrecords.entity.enums.PaymentType;
import com.medicalrecords.exception.ResourceNotFoundException;
import com.medicalrecords.repository.*;
import com.medicalrecords.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Имплементация на бизнес логиката за прегледи.
 */
@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationRepository examinationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DiagnosisRepository diagnosisRepository;

    /**
     * Създава нов преглед.
     */
    @Override
    public ExaminationResponse createExamination(
            ExaminationCreateRequest request
    ) {

        Doctor doctor = doctorRepository.findById(
                        request.getDoctorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Лекарят не беше намерен."
                        ));

        Patient patient = patientRepository.findById(
                        request.getPatientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Пациентът не беше намерен."
                        ));

        Diagnosis diagnosis = diagnosisRepository.findById(
                        request.getDiagnosisId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Диагнозата не беше намерена."
                        ));

        Examination examination = new Examination();

        examination.setDoctor(doctor);
        examination.setPatient(patient);
        examination.setDiagnosis(diagnosis);

        examination.setTreatment(
                request.getTreatment()
        );

        examination.setPrice(
                request.getPrice()
        );

        examination.setExaminationDate(
                LocalDateTime.now()
        );

        // Определяне на платеца според осигурителния статус
        examination.setPaymentType(
                patient.isInsured()
                        ? PaymentType.NHIF
                        : PaymentType.PATIENT
        );

        Examination savedExamination =
                examinationRepository.save(examination);

        return mapToResponse(savedExamination);
    }

    /**
     * Връща всички прегледи.
     */
    @Override
    public List<ExaminationResponse> getAllExaminations() {

        return examinationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Връща преглед по ID.
     */
    @Override
    public ExaminationResponse getExaminationById(Long id) {

        Examination examination =
                examinationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Прегледът не беше намерен."
                                ));

        return mapToResponse(examination);
    }

    /**
     * Обновява преглед.
     */
    @Override
    public ExaminationResponse updateExamination(
            Long id,
            ExaminationUpdateRequest request
    ) {

        Examination examination =
                examinationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Прегледът не беше намерен."
                                ));

        Diagnosis diagnosis =
                diagnosisRepository.findById(
                                request.getDiagnosisId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Диагнозата не беше намерена."
                                ));

        examination.setDiagnosis(
                diagnosis
        );

        examination.setTreatment(
                request.getTreatment()
        );

        examination.setPrice(
                request.getPrice()
        );

        // Преизчисляване на начина на плащане
        examination.setPaymentType(
                examination.getPatient().isInsured()
                        ? PaymentType.NHIF
                        : PaymentType.PATIENT
        );

        Examination updatedExamination =
                examinationRepository.save(examination);

        return mapToResponse(updatedExamination);
    }

    /**
     * Изтрива преглед.
     */
    @Override
    public void deleteExamination(Long id) {

        Examination examination =
                examinationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Прегледът не беше намерен."
                                ));

        examinationRepository.delete(examination);
    }

    /**
     * Конвертира Entity към DTO.
     */
    private ExaminationResponse mapToResponse(
            Examination examination
    ) {

        ExaminationResponse response =
                new ExaminationResponse();

        response.setId(
                examination.getId()
        );

        response.setExaminationDate(
                examination.getExaminationDate()
        );

        response.setDoctorName(
                examination.getDoctor().getFullName()
        );

        response.setPatientName(
                examination.getPatient().getFullName()
        );

        response.setDiagnosisName(
                examination.getDiagnosis().getName()
        );

        response.setTreatment(
                examination.getTreatment()
        );

        response.setPrice(
                examination.getPrice()
        );

        response.setPaymentType(
                examination.getPaymentType().name()
        );

        return response;
    }
}