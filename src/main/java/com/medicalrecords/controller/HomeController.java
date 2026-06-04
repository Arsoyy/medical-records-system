package com.medicalrecords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контролер за страниците на приложението.
 */
@Controller
public class HomeController {

    /**
     * Начална страница.
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * Страница с пациенти.
     */
    @GetMapping("/patients-page")
    public String patientsPage() {
        return "patients";
    }

    /**
     * Страница с лекари.
     */
    @GetMapping("/doctors-page")
    public String doctorsPage() {
        return "doctors";
    }

    /**
     * Страница с прегледи.
     */
    @GetMapping("/examinations-page")
    public String examinationsPage() {
        return "examinations";
    }

    @GetMapping("/diagnoses-page")
    public String diagnosesPage() {
        return "diagnoses";
    }

    @GetMapping("/sick-leaves-page")
    public String sickLeavesPage() {
        return "sick-leaves";
    }

    @GetMapping("/reports-page")
    public String reportsPage() {
        return "reports";
    }
}