package com.medicalrecords.controller;

import com.medicalrecords.dto.patient.*;
import com.medicalrecords.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контролер за работа с пациенти.
 */
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    /**
     * Създава пациент.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponse createPatient(
            @Valid @RequestBody
            PatientCreateRequest request
    ) {

        return patientService.createPatient(request);
    }

    /**
     * Връща всички пациенти.
     */
    @GetMapping
    public List<PatientResponse> getAllPatients() {

        return patientService.getAllPatients();
    }

    /**
     * Връща пациент по ID.
     */
    @GetMapping("/{id}")
    public PatientResponse getPatientById(
            @PathVariable Long id
    ) {

        return patientService.getPatientById(id);
    }

    /**
     * Обновява пациент.
     */
    @PutMapping("/{id}")
    public PatientResponse updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody
            PatientUpdateRequest request
    ) {

        return patientService.updatePatient(
                id,
                request
        );
    }

    /**
     * Изтрива пациент.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(
            @PathVariable Long id
    ) {

        patientService.deletePatient(id);
    }
}