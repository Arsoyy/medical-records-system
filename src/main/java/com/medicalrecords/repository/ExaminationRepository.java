package com.medicalrecords.repository;

import com.medicalrecords.entity.Doctor;
import com.medicalrecords.entity.Examination;
import com.medicalrecords.entity.Patient;
import com.medicalrecords.entity.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository слой за работа с прегледите.
 */
public interface ExaminationRepository
        extends JpaRepository<Examination, Long> {

    /**
     * Пациенти с дадена диагноза.
     */
    @Query("""
       SELECT DISTINCT e.patient
       FROM Examination e
       WHERE e.diagnosis.id = :diagnosisId
       """)
    List<Patient> findPatientsByDiagnosisId(
            Long diagnosisId
    );

    /**
     * Статистика за диагнози.
     */
    @Query("""
           SELECT e.diagnosis.name,
                  COUNT(e)
           FROM Examination e
           GROUP BY e.diagnosis.name
           ORDER BY COUNT(e) DESC
           """)
    List<Object[]> getDiagnosisStatistics();

    /**
     * Обща стойност на прегледите,
     * платени от пациентите.
     */
    @Query("""
           SELECT COALESCE(SUM(e.price), 0)
           FROM Examination e
           WHERE e.paymentType =
                 com.medicalrecords.entity.enums.PaymentType.PATIENT
           """)
    BigDecimal getTotalPatientRevenue();

    /**
     * Стойност на платените прегледи
     * по лекар.
     */
    @Query("""
           SELECT e.doctor.fullName,
                  COALESCE(SUM(e.price), 0)
           FROM Examination e
           WHERE e.paymentType =
                 com.medicalrecords.entity.enums.PaymentType.PATIENT
           GROUP BY e.doctor.fullName
           """)
    List<Object[]> getRevenueByDoctor();

    /**
     * Брой посещения при всеки лекар.
     */
    @Query("""
           SELECT e.doctor.fullName,
                  COUNT(e)
           FROM Examination e
           GROUP BY e.doctor.fullName
           """)
    List<Object[]> getVisitCountByDoctor();

    /**
     * Връща всички прегледи на даден пациент.
     */
    List<Examination> findByPatient(
            Patient patient
    );

    /**
     * Връща всички прегледи на даден лекар.
     */
    List<Examination> findByDoctor(
            Doctor doctor
    );

    /**
     * Връща прегледите на лекар за период.
     */
    List<Examination> findByDoctorAndExaminationDateBetween(
            Doctor doctor,
            LocalDateTime start,
            LocalDateTime end
    );
}