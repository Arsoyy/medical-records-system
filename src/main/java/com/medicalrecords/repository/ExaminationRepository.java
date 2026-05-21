package com.medicalrecords.repository;

import com.medicalrecords.entity.Doctor;
import com.medicalrecords.entity.Examination;
import com.medicalrecords.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository слой за работа с прегледите.
 */
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    /**
     * Връща всички прегледи на даден пациент.
     *
     * @param patient пациент
     * @return списък с прегледи
     */
    List<Examination> findByPatient(Patient patient);

    /**
     * Връща всички прегледи,
     * извършени от даден лекар.
     *
     * @param doctor лекар
     * @return списък с прегледи
     */
    List<Examination> findByDoctor(Doctor doctor);

    /**
     * Връща прегледите на лекар
     * за определен период.
     *
     * @param doctor лекар
     * @param start начална дата
     * @param end крайна дата
     * @return списък с прегледи
     */
    List<Examination> findByDoctorAndExaminationDateBetween(
            Doctor doctor,
            LocalDateTime start,
            LocalDateTime end
    );
}