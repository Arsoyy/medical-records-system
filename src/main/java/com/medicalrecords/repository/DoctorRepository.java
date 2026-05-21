package com.medicalrecords.repository;

import com.medicalrecords.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository слой за работа с лекарите.
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * Намира лекар по уникален идентификационен номер.
     *
     * @param doctorIdentifier идентификационен номер
     * @return Optional<Doctor>
     */
    Optional<Doctor> findByDoctorIdentifier(String doctorIdentifier);
}