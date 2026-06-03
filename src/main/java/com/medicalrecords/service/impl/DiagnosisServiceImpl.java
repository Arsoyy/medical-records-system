package com.medicalrecords.service.impl;

import com.medicalrecords.dto.diagnosis.*;
import com.medicalrecords.entity.Diagnosis;
import com.medicalrecords.exception.DuplicateResourceException;
import com.medicalrecords.exception.ResourceNotFoundException;
import com.medicalrecords.repository.DiagnosisRepository;
import com.medicalrecords.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Имплементация на бизнес логиката за диагнози.
 */
@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    /**
     * Създава нова диагноза.
     */
    @Override
    public DiagnosisResponse createDiagnosis(
            DiagnosisCreateRequest request
    ) {

        // Проверка за съществуващ код
        if (diagnosisRepository.findByCode(
                request.getCode()).isPresent()) {

            throw new DuplicateResourceException(
                    "Диагноза с този код вече съществува."
            );
        }

        Diagnosis diagnosis = new Diagnosis();

        diagnosis.setCode(
                request.getCode()
        );

        diagnosis.setName(
                request.getName()
        );

        diagnosis.setDescription(
                request.getDescription()
        );

        Diagnosis savedDiagnosis =
                diagnosisRepository.save(diagnosis);

        return mapToResponse(savedDiagnosis);
    }

    /**
     * Връща всички диагнози.
     */
    @Override
    public List<DiagnosisResponse> getAllDiagnoses() {

        return diagnosisRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Връща диагноза по ID.
     */
    @Override
    public DiagnosisResponse getDiagnosisById(Long id) {

        Diagnosis diagnosis =
                diagnosisRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Диагнозата не беше намерена."
                                ));

        return mapToResponse(diagnosis);
    }

    /**
     * Обновява диагноза.
     */
    @Override
    public DiagnosisResponse updateDiagnosis(
            Long id,
            DiagnosisUpdateRequest request
    ) {

        Diagnosis diagnosis =
                diagnosisRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Диагнозата не беше намерена."
                                ));

        diagnosis.setName(
                request.getName()
        );

        diagnosis.setDescription(
                request.getDescription()
        );

        Diagnosis updatedDiagnosis =
                diagnosisRepository.save(diagnosis);

        return mapToResponse(updatedDiagnosis);
    }

    /**
     * Изтрива диагноза.
     */
    @Override
    public void deleteDiagnosis(Long id) {

        Diagnosis diagnosis =
                diagnosisRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Диагнозата не беше намерена."
                                ));

        diagnosisRepository.delete(diagnosis);
    }

    /**
     * Конвертира Entity към DTO.
     */
    private DiagnosisResponse mapToResponse(
            Diagnosis diagnosis
    ) {

        DiagnosisResponse response =
                new DiagnosisResponse();

        response.setId(
                diagnosis.getId()
        );

        response.setCode(
                diagnosis.getCode()
        );

        response.setName(
                diagnosis.getName()
        );

        response.setDescription(
                diagnosis.getDescription()
        );

        return response;
    }
}