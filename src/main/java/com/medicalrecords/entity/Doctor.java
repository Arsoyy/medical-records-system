package com.medicalrecords.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity клас за лекарите в системата.
 */
@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Уникален идентификационен номер на лекаря.
     */
    @Column(nullable = false, unique = true)
    private String doctorIdentifier;

    /**
     * Пълно име на лекаря.
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * Специалност на лекаря.
     */
    @Column(nullable = false)
    private String specialty;

    /**
     * Показва дали лекарят може да бъде личен лекар.
     */
    @Column(nullable = false)
    private boolean canBePersonalDoctor;

    /**
     * User акаунтът на лекаря.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Пациентите, които са записани
     * при този личен лекар.
     */
    @OneToMany(mappedBy = "personalDoctor")
    private List<Patient> patients = new ArrayList<>();

    /**
     * Прегледите, извършени от лекаря.
     */
    @OneToMany(mappedBy = "doctor")
    private List<Examination> examinations = new ArrayList<>();

    /**
     * Болничните листове,
     * издадени от лекаря.
     */
    @OneToMany(mappedBy = "doctor")
    private List<SickLeave> sickLeaves = new ArrayList<>();
}