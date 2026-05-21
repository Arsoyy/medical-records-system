package com.medicalrecords.entity;

import com.medicalrecords.entity.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity клас за медицинските прегледи.
 */
@Entity
@Table(name = "examinations")
@Getter
@Setter
@NoArgsConstructor
public class Examination {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата и час на прегледа.
     */
    @Column(nullable = false)
    private LocalDateTime examinationDate;

    /**
     * Лекарят, извършил прегледа.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    /**
     * Пациентът, на когото е извършен прегледът.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    /**
     * Диагнозата, поставена при прегледа.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_id", nullable = false)
    private Diagnosis diagnosis;

    /**
     * Назначено лечение.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String treatment;

    /**
     * Цена на прегледа.
     *
     * Използваме BigDecimal вместо double,
     * защото работим с парични стойности.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Показва кой заплаща прегледа.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    /**
     * Болничен лист, издаден по време на прегледа.
     *
     * Един преглед може да има максимум един болничен лист.
     */
    @OneToOne(mappedBy = "examination",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private SickLeave sickLeave;
}