package com.medicalrecords.repository;

import com.medicalrecords.entity.Doctor;
import com.medicalrecords.entity.SickLeave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository слой за работа с болнични листове.
 */
public interface SickLeaveRepository extends JpaRepository<SickLeave, Long> {

    /**
     * Връща всички болнични,
     * издадени от даден лекар.
     *
     * @param doctor лекар
     * @return списък с болнични листове
     */
    List<SickLeave> findByDoctor(Doctor doctor);
}