package com.medicalrecords.entity.enums;

/**
 * Enum, който описва ролите в системата.
 *
 * ROLE_ADMIN  - има пълен достъп
 * ROLE_DOCTOR - може да работи с прегледи и пациенти
 * ROLE_PATIENT - вижда само собствената си информация
 */
public enum RoleType {

    ROLE_ADMIN,
    ROLE_DOCTOR,
    ROLE_PATIENT
}