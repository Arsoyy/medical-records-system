package com.medicalrecords.service.impl;

import com.medicalrecords.dto.sickleave.*;
import com.medicalrecords.entity.Examination;
import com.medicalrecords.entity.SickLeave;
import com.medicalrecords.exception.ResourceNotFoundException;
import com.medicalrecords.repository.ExaminationRepository;
import com.medicalrecords.repository.SickLeaveRepository;
import com.medicalrecords.service.SickLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Имплементация на бизнес логиката за болнични листове.
 */
@Service
@RequiredArgsConstructor
public class SickLeaveServiceImpl implements SickLeaveService {

    private final SickLeaveRepository sickLeaveRepository;
    private final ExaminationRepository examinationRepository;

    /**
     * Създава нов болничен лист.
     */
    @Override
    public SickLeaveResponse createSickLeave(
            SickLeaveCreateRequest request
    ) {

        Examination examination =
                examinationRepository.findById(
                                request.getExaminationId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Прегледът не беше намерен."
                                ));

        SickLeave sickLeave = new SickLeave();

        sickLeave.setStartDate(
                request.getStartDate()
        );

        sickLeave.setDays(
                request.getDays()
        );

        sickLeave.setExamination(
                examination
        );

        // Лекарят се взима от прегледа
        sickLeave.setDoctor(
                examination.getDoctor()
        );

        SickLeave savedSickLeave =
                sickLeaveRepository.save(sickLeave);

        return mapToResponse(savedSickLeave);
    }

    /**
     * Връща всички болнични листове.
     */
    @Override
    public List<SickLeaveResponse> getAllSickLeaves() {

        return sickLeaveRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Връща болничен лист по ID.
     */
    @Override
    public SickLeaveResponse getSickLeaveById(Long id) {

        SickLeave sickLeave =
                sickLeaveRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Болничният лист не беше намерен."
                                ));

        return mapToResponse(sickLeave);
    }

    /**
     * Обновява болничен лист.
     */
    @Override
    public SickLeaveResponse updateSickLeave(
            Long id,
            SickLeaveUpdateRequest request
    ) {

        SickLeave sickLeave =
                sickLeaveRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Болничният лист не беше намерен."
                                ));

        sickLeave.setStartDate(
                request.getStartDate()
        );

        sickLeave.setDays(
                request.getDays()
        );

        SickLeave updatedSickLeave =
                sickLeaveRepository.save(sickLeave);

        return mapToResponse(updatedSickLeave);
    }

    /**
     * Изтрива болничен лист.
     */
    @Override
    public void deleteSickLeave(Long id) {

        SickLeave sickLeave =
                sickLeaveRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Болничният лист не беше намерен."
                                ));

        sickLeaveRepository.delete(sickLeave);
    }

    /**
     * Конвертира Entity към DTO.
     */
    private SickLeaveResponse mapToResponse(
            SickLeave sickLeave
    ) {

        SickLeaveResponse response =
                new SickLeaveResponse();

        response.setId(
                sickLeave.getId()
        );

        response.setStartDate(
                sickLeave.getStartDate()
        );

        response.setDays(
                sickLeave.getDays()
        );

        response.setDoctorName(
                sickLeave.getDoctor().getFullName()
        );

        response.setPatientName(
                sickLeave.getExamination()
                        .getPatient()
                        .getFullName()
        );

        response.setExaminationId(
                sickLeave.getExamination().getId()
        );

        return response;
    }
}