package com.medicalrecords.repository;

import com.medicalrecords.entity.Doctor;
import com.medicalrecords.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository слой за работа с пациентите.
 */
public interface PatientRepository
        extends JpaRepository<Patient, Long> {

    /**
     * Намира пациент по ЕГН.
     *
     * @param egn ЕГН на пациента
     * @return Optional<Patient>
     */
    Optional<Patient> findByEgn(String egn);

    /**
     * Връща всички пациенти
     * на даден личен лекар.
     *
     * @param doctor личен лекар
     * @return списък с пациенти
     */
    List<Patient> findByPersonalDoctor(
            Doctor doctor
    );

    /**
     * Брой пациенти при всеки личен лекар.
     */
    @Query("""
           SELECT p.personalDoctor.fullName,
                  COUNT(p)
           FROM Patient p
           GROUP BY p.personalDoctor.fullName
           """)
    List<Object[]> getPatientCountByDoctor();
}