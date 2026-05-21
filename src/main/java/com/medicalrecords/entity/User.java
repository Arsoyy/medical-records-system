package com.medicalrecords.entity;

import com.medicalrecords.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity клас за потребителите в системата.
 *
 * Този клас се използва единствено за:
 * - authentication
 * - authorization
 * - Spring Security
 *
 * Медицинската информация НЕ се пази тук.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Username за вход в системата.
     *
     * Трябва да бъде уникален.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Криптирана парола.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Роля на потребителя.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    /**
     * Показва дали акаунтът е активен.
     */
    @Column(nullable = false)
    private boolean enabled = true;

    /**
     * Връзка към doctor профил.
     *
     * Само ако потребителят е лекар.
     */
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Doctor doctor;

    /**
     * Връзка към patient профил.
     *
     * Само ако потребителят е пациент.
     */
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Patient patient;
}