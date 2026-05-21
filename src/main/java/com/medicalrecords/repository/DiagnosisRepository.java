package com.medicalrecords.repository;

import com.medicalrecords.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository слой за работа с диагнози.
 */
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    /**
     * Намира диагноза по код.
     *
     * @param code код на диагнозата
     * @return Optional<Diagnosis>
     */
    Optional<Diagnosis> findByCode(String code);
}