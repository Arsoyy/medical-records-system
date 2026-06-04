package com.medicalrecords.service.impl;

import com.medicalrecords.dto.examination.ExaminationResponse;
import com.medicalrecords.dto.report.*;
import com.medicalrecords.entity.*;
import com.medicalrecords.exception.ResourceNotFoundException;
import com.medicalrecords.repository.*;
import com.medicalrecords.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ExaminationRepository examinationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final SickLeaveRepository sickLeaveRepository;

    @Override
    public List<PatientSimpleResponse> getPatientsByDiagnosis(
            Long diagnosisId
    ) {

        return examinationRepository
                .findPatientsByDiagnosisId(diagnosisId)
                .stream()
                .map(patient -> {

                    PatientSimpleResponse response =
                            new PatientSimpleResponse();

                    response.setId(patient.getId());
                    response.setFullName(patient.getFullName());
                    response.setEgn(patient.getEgn());

                    return response;

                })
                .toList();
    }

    @Override
    public DiagnosisStatisticResponse getMostCommonDiagnosis() {

        Object[] result =
                examinationRepository
                        .getDiagnosisStatistics()
                        .get(0);

        DiagnosisStatisticResponse response =
                new DiagnosisStatisticResponse();

        response.setDiagnosisName(
                (String) result[0]
        );

        response.setOccurrenceCount(
                ((Long) result[1])
        );

        return response;
    }

    @Override
    public List<PatientSimpleResponse> getPatientsByDoctor(
            Long doctorId
    ) {

        Doctor doctor =
                doctorRepository.findById(doctorId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Лекарят не е намерен."
                                ));

        return patientRepository
                .findByPersonalDoctor(doctor)
                .stream()
                .map(patient -> {

                    PatientSimpleResponse response =
                            new PatientSimpleResponse();

                    response.setId(patient.getId());
                    response.setFullName(patient.getFullName());
                    response.setEgn(patient.getEgn());

                    return response;

                })
                .toList();
    }

    @Override
    public RevenueResponse getTotalPatientRevenue() {

        RevenueResponse response =
                new RevenueResponse();

        response.setTotalAmount(
                examinationRepository
                        .getTotalPatientRevenue()
        );

        return response;
    }

    @Override
    public List<DoctorRevenueResponse> getRevenueByDoctor() {

        return examinationRepository
                .getRevenueByDoctor()
                .stream()
                .map(row -> {

                    DoctorRevenueResponse response =
                            new DoctorRevenueResponse();

                    response.setDoctorName(
                            (String) row[0]
                    );

                    response.setTotalAmount(
                            (java.math.BigDecimal) row[1]
                    );

                    return response;

                })
                .toList();
    }

    @Override
    public List<DoctorPatientCountResponse>
    getPatientCountByDoctor() {

        return patientRepository
                .getPatientCountByDoctor()
                .stream()
                .map(row -> {

                    DoctorPatientCountResponse response =
                            new DoctorPatientCountResponse();

                    response.setDoctorName(
                            (String) row[0]
                    );

                    response.setPatientCount(
                            ((Long) row[1])
                    );

                    return response;

                })
                .toList();
    }

    @Override
    public List<DoctorVisitCountResponse>
    getVisitCountByDoctor() {

        return examinationRepository
                .getVisitCountByDoctor()
                .stream()
                .map(row -> {

                    DoctorVisitCountResponse response =
                            new DoctorVisitCountResponse();

                    response.setDoctorName(
                            (String) row[0]
                    );

                    response.setVisitCount(
                            ((Long) row[1])
                    );

                    return response;

                })
                .toList();
    }

    @Override
    public List<ExaminationResponse> getPatientHistory(
            Long patientId
    ) {

        Patient patient =
                patientRepository.findById(patientId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Пациентът не е намерен."
                                ));

        return examinationRepository
                .findByPatient(patient)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ExaminationResponse>
    getExaminationsByDoctor(
            Long doctorId
    ) {

        Doctor doctor =
                doctorRepository.findById(doctorId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Лекарят не е намерен."
                                ));

        return examinationRepository
                .findByDoctor(doctor)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SickLeaveStatisticResponse
    getMonthWithMostSickLeaves() {

        Object[] row =
                sickLeaveRepository
                        .getSickLeaveCountByMonth()
                        .get(0);

        SickLeaveStatisticResponse response =
                new SickLeaveStatisticResponse();

        response.setValue(
                String.valueOf(row[0])
        );

        response.setCount(
                ((Long) row[1])
        );

        return response;
    }

    @Override
    public SickLeaveStatisticResponse
    getDoctorWithMostSickLeaves() {

        Object[] row =
                sickLeaveRepository
                        .getSickLeaveCountByDoctor()
                        .get(0);

        SickLeaveStatisticResponse response =
                new SickLeaveStatisticResponse();

        response.setValue(
                (String) row[0]
        );

        response.setCount(
                ((Long) row[1])
        );

        return response;
    }

    private ExaminationResponse mapToResponse(
            Examination examination
    ) {

        ExaminationResponse response =
                new ExaminationResponse();

        response.setId(examination.getId());
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
    @Override
    public List<ExaminationResponse>
    getDoctorExaminationsPeriod(
            Long doctorId,
            String startDate,
            String endDate
    ) {

        Doctor doctor =
                doctorRepository.findById(doctorId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Лекарят не е намерен."
                                ));

        LocalDateTime start =
                LocalDate.parse(startDate)
                        .atStartOfDay();

        LocalDateTime end =
                LocalDate.parse(endDate)
                        .atTime(
                                23,
                                59,
                                59
                        );

        return examinationRepository
                .findByDoctorAndExaminationDateBetween(
                        doctor,
                        start,
                        end
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}
