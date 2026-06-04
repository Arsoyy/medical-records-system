package com.medicalrecords.service;

import com.medicalrecords.dto.examination.ExaminationResponse;
import com.medicalrecords.dto.report.*;

import java.util.List;

public interface ReportService {

    /**
     * Пациенти с дадена диагноза.
     */
    List<PatientSimpleResponse> getPatientsByDiagnosis(
            Long diagnosisId
    );

    /**
     * Най-често срещана диагноза.
     */
    DiagnosisStatisticResponse getMostCommonDiagnosis();

    /**
     * Пациенти към даден личен лекар.
     */
    List<PatientSimpleResponse> getPatientsByDoctor(
            Long doctorId
    );

    /**
     * Обща стойност на платените от пациентите прегледи.
     */
    RevenueResponse getTotalPatientRevenue();

    /**
     * Стойност на платените прегледи по лекар.
     */
    List<DoctorRevenueResponse> getRevenueByDoctor();

    /**
     * Брой пациенти при всеки личен лекар.
     */
    List<DoctorPatientCountResponse> getPatientCountByDoctor();

    /**
     * Брой посещения при всеки лекар.
     */
    List<DoctorVisitCountResponse> getVisitCountByDoctor();

    /**
     * История на посещенията на пациент.
     */
    List<ExaminationResponse> getPatientHistory(
            Long patientId
    );

    /**
     * Прегледи по лекар.
     */
    List<ExaminationResponse> getExaminationsByDoctor(
            Long doctorId
    );

    /**
     * Месец с най-много болнични.
     */
    SickLeaveStatisticResponse getMonthWithMostSickLeaves();

    /**
     * Лекар с най-много болнични.
     */
    SickLeaveStatisticResponse getDoctorWithMostSickLeaves();

    /**
     * Прегледи по лекар за определен период.
     */
    List<ExaminationResponse> getDoctorExaminationsPeriod(
            Long doctorId,
            String startDate,
            String endDate
    );
}