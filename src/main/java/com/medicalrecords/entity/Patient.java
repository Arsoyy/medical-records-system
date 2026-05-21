package com.medicalrecords.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity клас за пациентите в системата.
 */
@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Пълно име на пациента.
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * Единен граждански номер.
     *
     * Трябва да бъде уникален.
     */
    @Column(nullable = false, unique = true, length = 10)
    private String egn;

    /**
     * Показва дали пациентът е здравноосигурен.
     */
    @Column(nullable = false)
    private boolean insured;

    /**
     * Личен лекар на пациента.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_doctor_id")
    private Doctor personalDoctor;

    /**
     * User акаунт на пациента.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * История на прегледите на пациента.
     */
    @OneToMany(mappedBy = "patient")
    private List<Examination> examinations = new ArrayList<>();
}