package com.medicalrecords.repository;

import com.medicalrecords.entity.Doctor;
import com.medicalrecords.entity.SickLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository слой за работа с болнични листове.
 */
public interface SickLeaveRepository
        extends JpaRepository<SickLeave, Long> {

    /**
     * Връща всички болнични,
     * издадени от даден лекар.
     *
     * @param doctor лекар
     * @return списък с болнични листове
     */
    List<SickLeave> findByDoctor(
            Doctor doctor
    );

    /**
     * Месец с най-много издадени болнични.
     */
    @Query("""
           SELECT MONTH(s.startDate),
                  COUNT(s)
           FROM SickLeave s
           GROUP BY MONTH(s.startDate)
           ORDER BY COUNT(s) DESC
           """)
    List<Object[]> getSickLeaveCountByMonth();

    /**
     * Лекари с най-много издадени болнични.
     */
    @Query("""
           SELECT s.doctor.fullName,
                  COUNT(s)
           FROM SickLeave s
           GROUP BY s.doctor.fullName
           ORDER BY COUNT(s) DESC
           """)
    List<Object[]> getSickLeaveCountByDoctor();
}