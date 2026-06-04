package com.medicalrecords.controller;

import com.medicalrecords.dto.examination.ExaminationResponse;
import com.medicalrecords.dto.report.*;
import com.medicalrecords.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контролер за справки.
 */
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    /**
     * Пациенти с дадена диагноза.
     */
    @GetMapping("/patients-by-diagnosis/{diagnosisId}")
    public List<PatientSimpleResponse> getPatientsByDiagnosis(
            @PathVariable Long diagnosisId
    ) {

        return reportService.getPatientsByDiagnosis(
                diagnosisId
        );
    }

    /**
     * Най-често срещана диагноза.
     */
    @GetMapping("/most-common-diagnosis")
    public DiagnosisStatisticResponse
    getMostCommonDiagnosis() {

        return reportService
                .getMostCommonDiagnosis();
    }

    /**
     * Пациенти към даден личен лекар.
     */
    @GetMapping("/patients-by-doctor/{doctorId}")
    public List<PatientSimpleResponse>
    getPatientsByDoctor(
            @PathVariable Long doctorId
    ) {

        return reportService
                .getPatientsByDoctor(
                        doctorId
                );
    }

    /**
     * Обща стойност на платените прегледи.
     */
    @GetMapping("/total-revenue")
    public RevenueResponse getTotalRevenue() {

        return reportService
                .getTotalPatientRevenue();
    }

    /**
     * Приходи по лекар.
     */
    @GetMapping("/revenue-by-doctor")
    public List<DoctorRevenueResponse>
    getRevenueByDoctor() {

        return reportService
                .getRevenueByDoctor();
    }

    /**
     * Брой пациенти по лекар.
     */
    @GetMapping("/patient-count-by-doctor")
    public List<DoctorPatientCountResponse>
    getPatientCountByDoctor() {

        return reportService
                .getPatientCountByDoctor();
    }

    /**
     * Брой посещения по лекар.
     */
    @GetMapping("/visit-count-by-doctor")
    public List<DoctorVisitCountResponse>
    getVisitCountByDoctor() {

        return reportService
                .getVisitCountByDoctor();
    }

    /**
     * История на пациент.
     */
    @GetMapping("/patient-history/{patientId}")
    public List<ExaminationResponse>
    getPatientHistory(
            @PathVariable Long patientId
    ) {

        return reportService
                .getPatientHistory(
                        patientId
                );
    }

    /**
     * Прегледи по лекар.
     */
    @GetMapping("/doctor-examinations/{doctorId}")
    public List<ExaminationResponse>
    getDoctorExaminations(
            @PathVariable Long doctorId
    ) {

        return reportService
                .getExaminationsByDoctor(
                        doctorId
                );
    }

    /**
     * Месец с най-много болнични.
     */
    @GetMapping("/top-sickleave-month")
    public SickLeaveStatisticResponse
    getTopSickLeaveMonth() {

        return reportService
                .getMonthWithMostSickLeaves();
    }

    /**
     * Лекар с най-много болнични.
     */
    @GetMapping("/top-sickleave-doctor")
    public SickLeaveStatisticResponse
    getTopSickLeaveDoctor() {

        return reportService
                .getDoctorWithMostSickLeaves();
    }

    @GetMapping("/doctor-examinations-period")
    public List<ExaminationResponse> getDoctorExaminationsPeriod(
            @RequestParam Long doctorId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {

        return reportService.getDoctorExaminationsPeriod(
                doctorId,
                startDate,
                endDate
        );
    }
}