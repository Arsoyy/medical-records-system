package com.medicalrecords.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity клас за диагнози.
 */
@Entity
@Table(name = "diagnoses")
@Getter
@Setter
@NoArgsConstructor
public class Diagnosis {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Код на диагнозата.
     */
    @Column(nullable = false, unique = true)
    private String code;

    /**
     * Име на диагнозата.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Описание на диагнозата.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Прегледите с тази диагноза.
     */
    @OneToMany(mappedBy = "diagnosis")
    private List<Examination> examinations = new ArrayList<>();
}