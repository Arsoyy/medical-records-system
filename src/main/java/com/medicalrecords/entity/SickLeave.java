package com.medicalrecords.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity клас за болнични листове.
 */
@Entity
@Table(name = "sick_leaves")
@Getter
@Setter
@NoArgsConstructor
public class SickLeave {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Начална дата на болничния.
     */
    @Column(nullable = false)
    private LocalDate startDate;

    /**
     * Брой дни на болничния.
     */
    @Column(nullable = false)
    private Integer days;

    /**
     * Прегледът, по който е издаден болничният.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examination_id", nullable = false)
    private Examination examination;

    /**
     * Лекарят, издал болничния лист.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}